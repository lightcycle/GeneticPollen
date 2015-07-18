package org.lightcycle.alife.geneticpollen.genetics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

public class PhenotypeProvider {
	private Reflections reflections;
	
	private Map<Class,Set<Class>> cachedImplementations;
	
	private Map<Class,Constructor[]> cachedConstructors;
	
	private Map<Constructor, Class[]> cachedParameterTypes;
		
	public PhenotypeProvider(String packagePrefix) {
		reflections = new Reflections(packagePrefix);
	}
	
	private Set<Class> getImplementations(Class type) {
		if (cachedImplementations == null) {
			cachedImplementations = new HashMap<Class,Set<Class>>();
		}
		
		Set<Class> implementations = cachedImplementations.get(type);
		if (implementations == null) {
			implementations = (Set)reflections.getSubTypesOf(type);
			cachedImplementations.put(type, implementations);
		}
		return implementations;
	}
	
	private Class getRandomImplementation(Class type, Iterator<Integer> geneticInput) throws PhenotypeProviderException {
		Set<Class> implementations = getImplementations(type);
		if (implementations == null || implementations.size() == 0) {
			return null;
		} else if (implementations.size() == 1) {
			return (Class)implementations.toArray()[0];
		} else {
			if (!geneticInput.hasNext()) {
				throw new PhenotypeProviderException("Reached end of genetic data.");
			}
			return (Class)implementations.toArray()[geneticInput.next() % implementations.size()];
		}
	}
	
	private Constructor[] getConstructors(Class type) {
		if (cachedConstructors == null) {
			cachedConstructors = new HashMap<Class, Constructor[]>();
		}
		
		Constructor[] constructors = cachedConstructors.get(type);
		if (constructors == null) {
			constructors = type.getConstructors();
			cachedConstructors.put(type, constructors);
		}
		return constructors;
	}
	
	private Constructor getRandomConstructor(Class type, Iterator<Integer> geneticInput) throws PhenotypeProviderException {
		Constructor[] constructors = getConstructors(type);
		if (constructors == null || constructors.length == 0) {
			return null;
		} else if (constructors.length == 1) {
			return constructors[0];
		} else {
			if (!geneticInput.hasNext()) {
				throw new PhenotypeProviderException("Reached end of genetic data.");
			}
			return constructors[geneticInput.next() % constructors.length];
		}
	}
	
	private Class[] getParameters(Constructor constructor) {
		if (cachedParameterTypes == null) {
			cachedParameterTypes = new HashMap<Constructor, Class[]>();
		}
		
		Class[] classes = cachedParameterTypes.get(constructor);
		if (classes == null) {
			classes = constructor.getParameterTypes();
			cachedParameterTypes.put(constructor, classes);
		}
		return classes;
	}

	
	public <T> T getInstance(Iterator<Integer> geneticInput, Class<T> type) throws PhenotypeProviderException  {
		if (type.isEnum()) {
			// Choose an enumeration value
			T[] values = type.getEnumConstants();
			if (!geneticInput.hasNext()) {
				throw new PhenotypeProviderException("Reached end of genetic data.");
			}
			return values[geneticInput.next() % values.length];
		} else {
			// Choose the class implementation to instantiate
			Class<? extends T> newtype = null;
			if (type.isInterface()) {
				// For interface, choose a class implementation of the interface
				newtype = getRandomImplementation(type, geneticInput);
				if (newtype == null) {
					throw new PhenotypeProviderException("Found no implementations of interface " + type.getName());						
				}
			} else {
				// For class, choose that class
				newtype = type;
			}
			
			// Choose constructor to use
			Constructor constructor = getRandomConstructor(newtype, geneticInput);

			// Determine constructor parameter values
			Class[] parametertypes = getParameters(constructor);
			Object[] parameters = new Object[parametertypes.length];
			for (int i = 0; i < parametertypes.length; i++) {
				if (parametertypes[i].isPrimitive()) {
					// Provide constant primitive value
					if ("int".equals(parametertypes[i].getName())) {
						if (!geneticInput.hasNext()) {
							throw new PhenotypeProviderException("Reached end of genetic data.");
						}
						parameters[i] = geneticInput.next();
					} else if ("boolean".equals(parametertypes[i].getName())) {
						if (!geneticInput.hasNext()) {
							throw new PhenotypeProviderException("Reached end of genetic data.");
						}
						parameters[i] = new Boolean(geneticInput.next() % 2 == 0);
					} else {
						throw new PhenotypeProviderException("Don't know how to construct primative parameter type " + parametertypes[i].getName());						
					}
				} else {
					// Recursively get class instance for a parameter
					parameters[i] = getInstance(geneticInput, parametertypes[i]);					
				}
			}
			
			// Instantiate class instance
			try {
				return (T) constructor.newInstance(parameters);
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException exception) {
				throw new PhenotypeProviderException("Failed to construct instance of " + newtype.getName(), exception);
			}
		}
	}
}

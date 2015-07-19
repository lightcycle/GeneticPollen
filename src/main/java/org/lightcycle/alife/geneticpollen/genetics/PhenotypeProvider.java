package org.lightcycle.alife.geneticpollen.genetics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.reflections.Reflections;

public class PhenotypeProvider {
	private Reflections reflections;
	
	private Map<Class,Set<Class>> cachedImplementations;
	
	private Map<Class,Constructor[]> cachedConstructors;
	
	private Map<Constructor, Class[]> cachedParameterTypes;

	public PhenotypeProvider(String packagePrefix) {
		reflections = new Reflections(packagePrefix);
		cachedImplementations = new HashMap<>();
		cachedConstructors = new HashMap<>();
		cachedParameterTypes = new HashMap<>();
	}

	public <T> T getInstance(Iterator<Integer> geneticInput, Class<T> type) throws PhenotypeProviderException {
		if (Integer.class.equals(type)) {
			return type.cast(nextGeneticDatum(geneticInput));
		} else if (Boolean.class.equals(type)) {
			return type.cast(nextGeneticDatum(geneticInput) % 2 == 0);
		} else if (type.isEnum()) {
			return getMember(type.getEnumConstants(), geneticInput);
		} else {
			// Choose the class implementation to instantiate
			if (type.isInterface()) {
				// For interface, choose a class implementation of the interface
				type = getMember(getImplementations(type).toArray(new Class[0]), geneticInput);
				if (type == null) {
					throw new PhenotypeProviderException("Found no implementations of interface " + type.getName());
				}
			}

			// Choose constructor to use
			Constructor constructor = getMember(getConstructors(type), geneticInput);

			// Determine constructor parameter values
			Class[] parametertypes = getParameters(constructor);
			Object[] parameters = new Object[parametertypes.length];
			for (int i = 0; i < parametertypes.length; i++) {
				parameters[i] = getInstance(geneticInput, parametertypes[i].isPrimitive()?ClassUtils.primitiveToWrapper(parametertypes[i]):parametertypes[i]);
			}

			// Instantiate class instance
			try {
				return (T) constructor.newInstance(parameters);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
				throw new PhenotypeProviderException("Failed to construct instance of " + type.getName(), exception);
			}
		}
	}

	private <M> M getMember(M[] array, Iterator<Integer> geneticInput) throws PhenotypeProviderException {
		if (array == null || array.length == 0) {
			return null;
		}
		return array[nextGeneticDatum(geneticInput) % array.length];
	}

	private Integer nextGeneticDatum(Iterator<Integer> geneticInput) throws PhenotypeProviderException {
		if (!geneticInput.hasNext()) {
			throw new PhenotypeProviderException("Reached end of genetic data.");
		}
		return geneticInput.next();
	}

	private Constructor[] getConstructors(Class type) {
		return cachedConstructors.computeIfAbsent(type, t -> {
			return type.getConstructors();
		});
	}

	private Class[] getParameters(Constructor constructor) {
		return cachedParameterTypes.computeIfAbsent(constructor, c -> {
			return c.getParameterTypes();
		});
	}

	private Set<Class> getImplementations(Class type) {
		return cachedImplementations.computeIfAbsent(type, t -> {
			return reflections.getSubTypesOf(type);
		});
	}
}

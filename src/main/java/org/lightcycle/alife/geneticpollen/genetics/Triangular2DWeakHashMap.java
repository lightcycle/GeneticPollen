package org.lightcycle.alife.geneticpollen.genetics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class Triangular2DWeakHashMap<K extends Comparable<K>, V> {
	private Map<K, Map<K, V>> map = new WeakHashMap<>();

	public void put(K key1, K key2, V value) {
		// Order keys
		if (key1.compareTo(key2) > 0) {
			K temp = key2;
			key2 = key1;
			key1 = temp;
		}
		
		map.computeIfAbsent(key1, k1 -> {
			return new WeakHashMap<K, V>();
		}).put(key2, value);
	}
	
	public V get(K key1, K key2) {
		// Order keys
		if (key1.compareTo(key2) > 0) {
			K temp = key2;
			key2 = key1;
			key1 = temp;
		}
		
		// Retrieve value
		Map<K, V> submap = map.get(key1);
		if (submap != null) {
			return submap.get(key2);
		}			
		return null;
	}
	
	public Set<K> getRelatedKeys(K key) {
		Set<K> set = new HashSet<K>();
		Map<K, V> submap = map.get(key);
		if (submap != null) {
			set.addAll(submap.keySet());
		}
		for (K key2 : map.keySet()) {
			if (map.get(key2).containsKey(key)) {
				set.add(key2);
			}
		}
		return set;
	}
	
	public Set<K> keySet() {
		return map.keySet();
	}
}

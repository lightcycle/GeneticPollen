package org.lightcycle.alife.geneticpollen.genetics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Genomes {
	private Triangular2DWeakHashMap<Genome, Integer> kinship;
	
	private long GENOME_ID_COUNTER = 0L;
	
	public Genomes() {
		kinship = new Triangular2DWeakHashMap<Genome, Integer>();
	}
	
	public Genome createRandomGenome(int size, Random random) {
		Genome genome = new Genome();
		genome.data = new int[size];
		for (int i = 0; i < genome.data.length; i++) {
			genome.data[i] = random.nextInt(Integer.MAX_VALUE);
		}
		return genome;
	}
	
	public Genome createGenome(int[] data) {
		Genome genome = new Genome();
		genome.data = data;
		return genome;
	}
		
	public class Genome implements Iterable<Integer>, Comparable<Genome> {	
		private Long id;
		
		private int[] data;
		
		private Genome() {
			id = ++GENOME_ID_COUNTER;
		}
		
		public Genome getMutant(double rate, Random random) {
			Genome genome = new Genome();
			genome.data = Arrays.copyOf(data, data.length);
			for (int i = 0; i < genome.data.length; i++) {
				if (random.nextDouble() < rate) {
					genome.data[i] = random.nextInt(Integer.MAX_VALUE);
				}
			}
			genome.addKinship(this);
			return genome;
		}
		
		private void addKinship(Genome parent) {
			for (Genome other : kinship.getRelatedKeys(parent)) {
				kinship.put(this, other, kinship.get(other, parent) + 1);				
			}
			kinship.put(this, parent, 1);
		}
		
		public int getKinship(Genome genome) {
			if (kinship == null) {
				return Integer.MAX_VALUE;
			}
			
			if (this == genome) {
				return 0;
			}
			
			Integer k = kinship.get(this, genome);
			return (k == null) ? Integer.MAX_VALUE : k;
		}
		
		public Iterator<Integer> iterator() {
			return new GenomeIterator();
		}
		
		private class GenomeIterator implements Iterator<Integer> {
			private int i = 0;
			
			public boolean hasNext() {
				return i < data.length;
			}

			public Integer next() {
				return data[i++];
			}

			@Override
			public void remove() {
			}
		}

		@Override
		public int compareTo(Genome other) {
			return id.compareTo(other.id);
		}
		
		@Override	
		public int hashCode() {
			return id.hashCode();
		}
		
		@Override	
		public boolean equals(Object obj) {
			if (!(obj instanceof Genome)) {
				return false;
			}
			return id == ((Genome)obj).id;
		}
	}
}

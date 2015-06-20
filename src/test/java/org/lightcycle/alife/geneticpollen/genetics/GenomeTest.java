package org.lightcycle.alife.geneticpollen.genetics;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.lightcycle.alife.geneticpollen.genetics.Genomes;
import org.lightcycle.alife.geneticpollen.genetics.Genomes.Genome;

public class GenomeTest {
	private Genomes genomes = new Genomes();
	Random random = new Random(1L);
	
	@Test
	public void testIdentical() {
		Genome genome = genomes.createRandomGenome(100, random);
		assertEquals(0, genome.getKinship(genome));
	}

	@Test
	public void testUnrelated() {
		Genome genome1 = genomes.createRandomGenome(100, random);
		Genome genome2 = genomes.createRandomGenome(100, random);
		assertEquals(Integer.MAX_VALUE, genome1.getKinship(genome2));
		assertEquals(Integer.MAX_VALUE, genome2.getKinship(genome1));
	}
	
	@Test
	public void testKinshipToGrandparent() {
		Genome grandparent = genomes.createRandomGenome(100, random);		
		Genome genome = grandparent.getMutant(0, random).getMutant(0, random).getMutant(0, random);
		assertEquals(3, grandparent.getKinship(genome));
		assertEquals(3, genome.getKinship(grandparent));
	}	
	
	@Test
	public void testKinshipCommonGrandparent() {
		Genome grandparent = genomes.createRandomGenome(100, random);
		Genome genome1 = grandparent.getMutant(0, random).getMutant(0, random).getMutant(0, random);
		Genome genome2 = grandparent.getMutant(0, random).getMutant(0, random).getMutant(0, random);
		assertEquals(6, genome1.getKinship(genome2));
		assertEquals(6, genome2.getKinship(genome1));
	}
}

package org.lightcycle.alife.pollenevo.genetics;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.lightcycle.alife.pollenevo.genetics.Genomes.Genome;
import org.lightcycle.alife.pollenevo.genetics.PhenotypeProvider;
import org.lightcycle.alife.pollenevo.genetics.PhenotypeProviderException;
import org.lightcycle.alife.pollenevo.rules.BooleanSource;
import org.lightcycle.alife.pollenevo.rules.Rule;

public class PhenotypeProviderTest {
	private Genomes genomes = new Genomes();
	private Random random = new Random(1L);
	private PhenotypeProvider provider = new PhenotypeProvider("org.lightcycle.alife.pollenevo");
	
	@Test
	public void canProduceInterface() {
		try {
			provider.getInstance(genomes.createRandomGenome(100, random).iterator(), Rule.class);
		} catch (PhenotypeProviderException exception) {
			fail(exception.getMessage());
		}
	}
	
	@Test
	public void productionIsDeterministic() {
		Genome genome = genomes.createRandomGenome(100, random);
		BooleanSource source1 = null, source2 = null;
		try {
			source1 = provider.getInstance(genome.iterator(), BooleanSource.class);
			source2 = provider.getInstance(genome.iterator(), BooleanSource.class);
		} catch (PhenotypeProviderException exception) {
			fail(exception.getMessage());
		}
		assertEquals(source1.toString(), source2.toString());
	}
	
	@Test
	public void differentGenomeProducesDifferentPhenotype() {
		Genome genome1 = genomes.createRandomGenome(100, random);
		Genome genome2 = genomes.createRandomGenome(100, random);
		BooleanSource source1 = null, source2 = null;
		try {
			source1 = provider.getInstance(genome1.iterator(), BooleanSource.class);
			source2 = provider.getInstance(genome2.iterator(), BooleanSource.class);
		} catch (PhenotypeProviderException exception) {
			fail(exception.getMessage());
		}
		assertNotEquals(source1.toString(), source2.toString());
	}
}

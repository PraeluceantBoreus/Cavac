package io.github.praeluceantboreus.cavac.generator;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

public class VacuumGenerator extends ChunkGenerator
{
	@SuppressWarnings("deprecation")
	@Override
	public byte[] generate(World world, Random random, int x, int z)
	{
		// TODO Auto-generated method stub
		return super.generate(world, random, x, z);
	}

	@SuppressWarnings("deprecation")
	@Override
	public short[][] generateExtBlockSections(World world, Random random, int x, int z, BiomeGrid biomes)
	{
		// TODO Auto-generated method stub
		return super.generateExtBlockSections(world, random, x, z, biomes);
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world)
	{
		// TODO Auto-generated method stub
		return super.getDefaultPopulators(world);
	}

	@Override
	public Location getFixedSpawnLocation(World world, Random random)
	{
		// TODO Auto-generated method stub
		return super.getFixedSpawnLocation(world, random);
	}
}

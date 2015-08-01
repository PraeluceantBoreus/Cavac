package io.github.praeluceantboreus.cavac.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;

public class VacuumGenerator extends ChunkGenerator
{
	private int xWidth, zWidth, height, begin;
	private Material ground;
	private ArrayList<TreeType> trees;
	private ArrayList<ItemStack> items;

	public VacuumGenerator(int xWidth, int zWidth, int height, int begin, Material ground, ArrayList<TreeType> trees, ArrayList<ItemStack> items)
	{
		super();
		this.xWidth = xWidth;
		this.zWidth = zWidth;
		this.height = height;
		this.begin = begin;
		this.ground = ground;
		this.trees = trees;
		this.items = items;
	}

	@Override
	public byte[] generate(World world, Random random, int x, int z)
	{
		byte[] result = new byte[32768];
		return result;
	}

	public static int getBytePosition(int x, int y, int z)
	{
		return (x * 16 + z) * 128 + y;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world)
	{
		ArrayList<BlockPopulator> ret = new ArrayList<>();
		ret.add(new SkyPopulator(xWidth, zWidth, height, begin, ground, trees, items));
		return ret;
	}

	@Override
	public Location getFixedSpawnLocation(World world, Random random)
	{
		return new Location(world, xWidth / 2, begin + 2, zWidth / 2);
	}
}

package io.github.praeluceantboreus.cavac.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class SkyPopulator extends BlockPopulator
{

	private int xWidth, zWidth, height, begin;
	private Material ground;
	private ArrayList<TreeType> trees, originalTrees;

	public SkyPopulator(int xWidth, int zWidth, int height, int begin, Material ground, ArrayList<TreeType> trees)
	{
		super();
		this.xWidth = xWidth;
		this.zWidth = zWidth;
		this.height = height;
		this.begin = begin;
		this.ground = ground;
		this.trees = new ArrayList<>(trees);
		this.originalTrees = new ArrayList<>(trees);
	}

	@Override
	public void populate(World arg0, Random arg1, Chunk arg2)
	{
		for (int x = 0; x < xWidth && x < arg2.getX() * 16 + 16; x++)
		{
			for (int z = 0; z < zWidth && z < arg2.getZ() * 16 + 16; z++)
			{
				for (int y = begin; begin - y < height; y--)
				{
					Location loc = new Location(arg0, x, y, z);
					loc.getBlock().setType(ground);
				}
				Location loc = new Location(arg0, x, begin + 1, z);
				if ((x == xWidth - 1 || x == 0) && (z == zWidth - 1 || z == 0) && loc.getBlock().getType().equals(Material.AIR))
				{
					if (trees.size() < 1)
						trees = new ArrayList<>(originalTrees);
					Collections.shuffle(trees, arg1);
					TreeType type = trees.get(0);
					trees.remove(0);
					arg0.generateTree(loc, type);
				}
			}
		}
	}

}

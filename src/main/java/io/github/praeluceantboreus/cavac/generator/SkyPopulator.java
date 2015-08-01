package io.github.praeluceantboreus.cavac.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;

public class SkyPopulator extends BlockPopulator
{

	private int xWidth, zWidth, height, begin;
	private Material ground;
	private ArrayList<TreeType> trees, originalTrees;
	private ArrayList<ItemStack> items;
	private ArrayList<TreeType> fourBlockTrees;

	public SkyPopulator(int xWidth, int zWidth, int height, int begin, Material ground, ArrayList<TreeType> trees, ArrayList<ItemStack> items)
	{
		super();
		this.xWidth = xWidth;
		this.zWidth = zWidth;
		this.height = height;
		this.begin = begin;
		this.ground = ground;
		this.trees = new ArrayList<>(trees);
		this.originalTrees = new ArrayList<>(trees);
		this.items = items;
		fourBlockTrees = new ArrayList<>();
		fourBlockTrees.add(TreeType.DARK_OAK);
		fourBlockTrees.add(TreeType.JUNGLE);
	}

	@Override
	public void populate(World arg0, Random arg1, Chunk arg2)
	{
		for (int x = arg2.getX() * 16; x < xWidth && x < arg2.getX() * 16 + 16 && x >= 0; x++)
		{
			for (int z = arg2.getZ() * 16; z < zWidth && z < arg2.getZ() * 16 + 16 && z >= 0; z++)
			{
				for (int y = begin - height + 1; y <= begin + 1; y++)
				{
					Location loc = new Location(arg0, x, y, z);
					if (y < begin + 1)
						loc.getBlock().setType(ground);
					else
					{
						if ((x == xWidth - 1 || x == 0) && (z == zWidth - 1 || z == 0))
						{
							if (trees.size() < 1)
								trees = new ArrayList<>(originalTrees);
							Collections.shuffle(trees, arg1);
							TreeType type = trees.get(0);
							Location treeLoc = loc.clone();
							if ((x == xWidth - 1 || z == zWidth - 1) && fourBlockTrees.contains(type))
								treeLoc.add(-1, 0, -1);
							trees.remove(0);
							arg0.generateTree(treeLoc, type);
						}
						if (x == ((int) xWidth / 2) && z == ((int) zWidth / 2))
						{
							loc.getBlock().setType(Material.CHEST);
							Chest chest = (Chest) loc.getBlock().getState();
							Collections.shuffle(items, arg1);
							chest.getInventory().addItem(items.toArray(new ItemStack[items.size()]));
						}
					}
				}
			}
		}
	}
}

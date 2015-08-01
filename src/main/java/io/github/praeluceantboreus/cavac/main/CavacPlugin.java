package io.github.praeluceantboreus.cavac.main;

import io.github.praeluceantboreus.cavac.generator.VacuumGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CavacPlugin extends JavaPlugin
{
	private int xWidth, zWidth, height, begin;
	private Material ground;
	private ArrayList<TreeType> trees;
	private ArrayList<ItemStack> items;
	private TreeType[] treeArray = { TreeType.ACACIA, TreeType.BIRCH, TreeType.COCOA_TREE, TreeType.DARK_OAK, TreeType.REDWOOD, TreeType.TREE, TreeType.SWAMP };

	@SuppressWarnings("unchecked")
	@Override
	public void onEnable()
	{
		generateConfig();
		xWidth = getConfig().getInt("xwidth", 16);
		zWidth = getConfig().getInt("zwidth", 16);
		height = getConfig().getInt("height", 4);
		ground = Material.valueOf(getConfig().getString("ground", Material.GRASS.toString()));
		begin = getConfig().getInt("begin", 63);
		trees = new ArrayList<>();
		List<String> treesString = getConfig().getStringList("trees");
		for (String tree : treesString)
			trees.add(TreeType.valueOf(tree));
		if (trees.size() < 1)
			trees = new ArrayList<>(Arrays.asList(treeArray));
		System.out.println(getConfig().get("startitems").getClass());
		items = new ArrayList<>();
		List<Map<?, ?>> savedItems = getConfig().getMapList("startitems");
		for (Map<?, ?> savedItem : savedItems)
			items.add(ItemStack.deserialize((Map<String, Object>) savedItem));
		super.onEnable();
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new VacuumGenerator(xWidth, zWidth, height, begin, ground, trees, items);
	}

	private void generateConfig()
	{
		ArrayList<ItemStack> items = new ArrayList<>();
		items.add(new ItemStack(Material.WATER_BUCKET));
		items.add(new ItemStack(Material.LAVA_BUCKET));
		items.add(new ItemStack(Material.SEEDS, 4));
		ArrayList<Map<String, Object>> itemSer = new ArrayList<>();
		for (ItemStack is : items)
			itemSer.add(is.serialize());
		getConfig().addDefault("xwidth", 16);
		getConfig().addDefault("zwidth", 16);
		getConfig().addDefault("height", 4);
		getConfig().addDefault("ground", Material.GRASS.toString());
		getConfig().addDefault("begin", 63);
		ArrayList<String> treeNames = new ArrayList<>();
		for (TreeType tt : treeArray)
			treeNames.add(tt.toString());
		getConfig().addDefault("trees", treeNames);
		getConfig().addDefault("startitems", itemSer);

		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
	}
}

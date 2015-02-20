import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Infusion_helper {
	
	private Infusion plugin;
	private Material[] canenchant;
	
	public Infusion_helper(Infusion plugin) {
		this.plugin = plugin;
		
		String[] liste = plugin.config.getString("can_enchant.materials").split(",");
			canenchant = new Material[liste.length];
				for(int i = 0; i < liste.length; i++) {
					canenchant[i] = Material.valueOf(liste[i]);
				}
	}

	
	protected boolean canEnchant(Material m) {
		for(Material can : canenchant) {
			if(can == m) {return true;}
		}
		return false;
	}
	
	
	protected void add(ItemStack item) {
			ItemMeta m = item.getItemMeta();
			String name = ChatColor.RED + "[Infused]";
			m.setDisplayName(name);
			item.setItemMeta(m);
		item.addUnsafeEnchantment(plugin.enchant, 1);
	}
	
	
	protected void remove(ItemStack item) {
			ItemMeta m = item.getItemMeta();
			String name = null;
			m.setDisplayName(name);
			item.setItemMeta(m);
		item.removeEnchantment(plugin.enchant);
	}
	
	
	protected Block[] getBlocks(Block block, BlockFace face) {
		Block[] blocks = new Block[8];
			if(face == BlockFace.UP || face == BlockFace.DOWN) {
				blocks[0] = block.getRelative(BlockFace.NORTH_WEST);
				blocks[1] = block.getRelative(BlockFace.NORTH);
				blocks[2] = block.getRelative(BlockFace.NORTH_EAST);
				blocks[3] = block.getRelative(BlockFace.WEST);
				blocks[4] = block.getRelative(BlockFace.EAST);
				blocks[5] = block.getRelative(BlockFace.SOUTH_WEST);
				blocks[6] = block.getRelative(BlockFace.SOUTH_EAST);
				blocks[7] = block.getRelative(BlockFace.SOUTH);
			}else {
				if(face == BlockFace.NORTH || face == BlockFace.SOUTH) {
					blocks[0] = block.getRelative(BlockFace.WEST);
					blocks[1] = block.getRelative(BlockFace.EAST);
					blocks[2] = block.getRelative(BlockFace.UP);
					blocks[3] = block.getRelative(BlockFace.DOWN);
					blocks[4] = blocks[2].getRelative(BlockFace.WEST);
					blocks[5] = blocks[2].getRelative(BlockFace.EAST);
					blocks[6] = blocks[3].getRelative(BlockFace.WEST);
					blocks[7] = blocks[3].getRelative(BlockFace.EAST);
				}else {
					blocks[0] = block.getRelative(BlockFace.NORTH);
					blocks[1] = block.getRelative(BlockFace.SOUTH);
					blocks[2] = block.getRelative(BlockFace.UP);
					blocks[3] = block.getRelative(BlockFace.DOWN);
					blocks[4] = blocks[2].getRelative(BlockFace.NORTH);
					blocks[5] = blocks[2].getRelative(BlockFace.SOUTH);
					blocks[6] = blocks[3].getRelative(BlockFace.NORTH);
					blocks[7] = blocks[3].getRelative(BlockFace.SOUTH);
				}
			}
		return blocks;
	}
	
	
	protected boolean hasInfusion(Map<Enchantment, Integer> enchantments) {
		for (Enchantment e : enchantments.keySet()) {
			if(e.equals(plugin.enchant)) {return true;}
		}
		return false;
	}


	public boolean hasEnabled(ItemStack itemInHand) {
		Map<Enchantment, Integer> enchantments = itemInHand.getEnchantments();
		for (Enchantment e : enchantments.keySet()) {
			if(e.equals(plugin.enchant)) {return true;}
		}
		return false;
	}


	public int check(Material type) {
		if(type == Material.WOOD_HOE || type == Material.STONE_HOE || type == Material.IRON_HOE || type == Material.DIAMOND_HOE || type == Material.GOLD_HOE) {
			return 0;
		}else if(type == Material.WOOD_SPADE || type == Material.STONE_SPADE || type == Material.IRON_SPADE || type == Material.DIAMOND_SPADE || type == Material.GOLD_SPADE) {
			return 1;
		}else if(type == Material.WOOD_PICKAXE || type == Material.STONE_PICKAXE || type == Material.IRON_PICKAXE || type == Material.DIAMOND_PICKAXE || type == Material.GOLD_PICKAXE) {
			return 2;
		}else if(type == Material.WOOD_AXE || type == Material.STONE_AXE || type == Material.IRON_AXE || type == Material.DIAMOND_AXE || type == Material.GOLD_AXE) {
			return 3;
		}
		return 4;
	}


	public void dostuff(int i, Block block) {
		switch(i) {
			case 0:
				if(block.getType() == Material.DIRT || block.getType() == Material.GRASS) {
					block.setType(Material.SOIL);
				}
				break;
			case 1:
					block.breakNaturally();
				break;
			case 2:
					block.breakNaturally();
				break;
			case 3:
					block.breakNaturally();
				break;
		}
	}

	
}

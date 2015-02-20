import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Infusion_events implements Listener {
	
	private Infusion plugin;

	public Infusion_events(Infusion plugin) {
		this.plugin = plugin;
	}

	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(plugin.toggle) {
			if(p.getGameMode().equals(GameMode.SURVIVAL)) {
				if(p.getItemInHand() != null) {
					if(plugin.helper.hasEnabled(p.getItemInHand())) {
						if(p.hasPermission("infusion.use")) {
							List<Block> blocks = p.getLastTwoTargetBlocks(null, 10);
							if (blocks.size() > 1) {
							  BlockFace face = blocks.get(1).getFace(blocks.get(0));
								Block[] liste = plugin.helper.getBlocks(e.getBlock(), face);
								int i = plugin.helper.check(p.getItemInHand().getType());
								for(Block block : liste) {
									if(block != null) {
										plugin.helper.dostuff(i, block);
									}
								}						
							}
						}	
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent e) {	
		Player p = e.getPlayer();
		if(plugin.toggle) {
			if (p.isSneaking()) {
				if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(p.hasPermission("infusion.toggle")) {
						if(e.hasItem()) {
							ItemStack item = e.getItem();
							if(plugin.helper.canEnchant(item.getType())) {
								if(plugin.helper.hasInfusion(item.getEnchantments())) {
									plugin.helper.remove(item);
								}else {
									plugin.helper.add(item);
								}
								p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1F, 1F);
								return;
							}
						}
					}
				}
			}
		}
	}
	
	
}

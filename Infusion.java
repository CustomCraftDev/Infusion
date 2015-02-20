
import java.lang.reflect.Field;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Infusion extends JavaPlugin {
	private String noperm;
	private String offall;
	private String onall;
	protected String prefix;
	
	protected boolean toggle = true;
	protected boolean debug = false;
	protected boolean update = false;
	
	protected FileConfiguration config;
	protected Infusion_helper helper;
	protected Infusion_enchant enchant;
	
	
	public void onEnable() {
		new Updater(this);
		loadconfig();
		
		resetEnchants();
		
		helper = new Infusion_helper(this);
    	getServer().getPluginManager().registerEvents(new Infusion_events(this), this);
    	
    	try {
    	    Field f = Enchantment.class.getDeclaredField("acceptingNew");
    	    f.setAccessible(true);
    	    f.set(null, true);
    	} catch (Exception e1) {}
    	
    	try {  	    
    		enchant = new Infusion_enchant(config.getInt("can_enchant.id"));
    		Enchantment.registerEnchantment(enchant);
    	} catch (Exception e2){}
	}

	
	private void resetEnchants() {
		try {
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			 
			byIdField.setAccessible(true);
			byNameField.setAccessible(true);
			 
			@SuppressWarnings("unchecked")
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			@SuppressWarnings("unchecked")
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);
			 
			if(byId.containsKey(config.getInt("can_enchant.id")))
			byId.remove(config.getInt("can_enchant.id"));
			 
			if(byName.containsKey("Infusion"))
			byName.remove("Infusion");
		} catch (Exception ignored) { }
	}


	private void loadconfig(){
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		
		debug = config.getBoolean("debug");
		noperm = ChatColor.translateAlternateColorCodes('&', config.getString("msg.noperm"));
		prefix = ChatColor.translateAlternateColorCodes('&', config.getString("msg.prefix"));
		
		offall = ChatColor.translateAlternateColorCodes('&', config.getString("msg.offall"));
		onall = ChatColor.translateAlternateColorCodes('&', config.getString("msg.onall"));
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		boolean isplayer = false;
		Player p = null;
		
		if ((sender instanceof Player)) {
			p = (Player)sender;
			isplayer = true;
		}
			
		if(cmd.getName().equalsIgnoreCase("infusion") && args.length == 1){
								
			// offall
			if(args[0].equalsIgnoreCase("offall")){
				if(isplayer){
					if(p.hasPermission("infusion.toggleall")){
						toggle = false;
						getServer().broadcastMessage(prefix + offall);
						System.out.println(ChatColor.stripColor(prefix + offall + " by " + p.getName()));
					return true;
				}
					else{
						p.sendMessage(noperm);
						return true;
					}
				}
				else{
						toggle = false;
						System.out.println(ChatColor.stripColor(prefix + offall));
					return true;
				}
			}
			
			// onall
			if(args[0].equalsIgnoreCase("onall")){
				if(isplayer){
					if(p.hasPermission("infusion.toggleall")){
						toggle = true;
						getServer().broadcastMessage(prefix + onall);
						System.out.println(ChatColor.stripColor(prefix + onall + " by " + p.getName()));
					return true;
				}
					else{
						p.sendMessage(noperm);
						return true;
					}
				}
				else{
						toggle = true;
						System.out.println(ChatColor.stripColor(prefix + onall));
					return true;
				}
			}
				
		}
		
		// nothing to do here \o/
		return false;
	}
	
	
	protected void say(Player p, boolean b) {
		if(b) {
			System.out.println(ChatColor.stripColor(prefix + "------------------------------------------------"));
			System.out.println(ChatColor.stripColor(prefix + " Infusion is outdated. Get the new version here:"));
			System.out.println(ChatColor.stripColor(prefix + " http://www.pokemon-online.xyz/plugin"));
			System.out.println(ChatColor.stripColor(prefix + "------------------------------------------------"));
		}else {
		   	p.sendMessage(prefix + "------------------------------------------------");
		   	p.sendMessage(prefix + " Infusion is outdated. Get the new version here:");
		   	p.sendMessage(prefix + " http://www.pokemon-online.xyz/plugin");
		   	p.sendMessage(prefix + "------------------------------------------------");
		}
	}
	
}

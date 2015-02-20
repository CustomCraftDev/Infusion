import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

public class Infusion_enchant extends EnchantmentWrapper{
 
	public Infusion_enchant(int id) {
		super(id);
	}
	 
	@Override
	public boolean canEnchantItem(ItemStack item) {
		return true;
	}
	 
	@Override
	public EnchantmentTarget getItemTarget() {
		return null;
	}
	 
	@Override
	public int getMaxLevel() {
		return 2;
	}
	 
	@Override
	public String getName() {
		return "Infusion";
	}
	 
	@Override
	public int getStartLevel() {
		return 1;
	}
 
}

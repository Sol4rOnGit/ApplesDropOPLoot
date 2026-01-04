package me.hiresh.applesdropoploot.applesDropOPLoot;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Random;

//Imports
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.Material;

//Events
import org.bukkit.event.Listener; //Need to import this into Listener so it works
import org.bukkit.event.player.PlayerDropItemEvent;

//Entities
import org.bukkit.entity.Player;
import org.bukkit.entity.Item;

//Inventory stuff
import org.bukkit.inventory.ItemStack;

public final class ApplesDropOPLoot extends JavaPlugin implements Listener{

    //Variables
    private static final Random random = new Random();

    //Flags
    private static boolean isEnabled = false;

    //Loot table and helper functions
    private static final ItemStack[] LootTable = {
            //Diamond and derivatives
            new ItemStack(Material.DIAMOND, 1),
            new ItemStack(Material.DIAMOND_AXE, 1),
            new ItemStack(Material.DIAMOND_BLOCK, 1),
            new ItemStack(Material.DIAMOND_BOOTS, 1),
            new ItemStack(Material.DIAMOND_HOE, 1),
            new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
            new ItemStack(Material.DIAMOND_HELMET, 1),
            new ItemStack(Material.DIAMOND_LEGGINGS, 1),
            new ItemStack(Material.DIAMOND_PICKAXE, 1),
            new ItemStack(Material.DIAMOND_SWORD, 1),

            //Netherite and derivatives
            new ItemStack(Material.NETHERITE_SPEAR, 1),
            new ItemStack(Material.NETHERITE_SWORD, 1),

            //Flight & Teleportation
            new ItemStack(Material.ELYTRA, 1),
            new ItemStack(Material.ENDER_PEARL, 1),
            new ItemStack(Material.FIREWORK_ROCKET, 8),

            //Bows & Arrows
            new ItemStack(Material.BOW, 1),
            new ItemStack(Material.ARROW, 16),
            new ItemStack(Material.SPECTRAL_ARROW, 8),

            //Other items
            new ItemStack(Material.IRON_INGOT, 3),
            new ItemStack(Material.COAL, 1),
            new ItemStack(Material.WATER_BUCKET, 1),

            //Food
            new ItemStack(Material.COOKED_PORKCHOP, 32),
            new ItemStack(Material.GOLDEN_APPLE, 2),
            new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1)
    };

    private static ItemStack getRandomOPLoot(){
        int index = random.nextInt(LootTable.length);
        return LootTable[index];
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this); //This is necessary for the plugin to run
        isEnabled = true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if(!isEnabled) {
            return;
        }

        Player player = event.getPlayer(); //Player that dropped the item cached
        Item droppedItem = event.getItemDrop(); //Get the ITEM of the dropped item -> Physical item. It's an entity
        ItemStack droppedItemStack = droppedItem.getItemStack(); //Get the ITEM STACK - which is the stack of items used, it is a data type. Amount of items

        if(droppedItemStack.getType() != Material.APPLE){ //All blocks are "Materials"
            return;
        }

        droppedItem.remove(); //Destroys the item

        World world = player.getWorld(); //Get the current world that the player is in
        Location location = droppedItem.getLocation(); //Get the location of dropped item

        final int numItems = random.nextInt(4); //Max 3

        for (int i = 0; i < numItems; i++){
            ItemStack currentItem = getRandomOPLoot();
            world.dropItemNaturally(location, currentItem);
        }
    }
}

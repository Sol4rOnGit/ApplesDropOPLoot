package me.hiresh.applesdropoploot.applesDropOPLoot;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

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

    //Flags
    private static boolean isEnabled = false;

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

        droppedItem.remove(); //Destroys an items

        //Spawn from a loot table, a bunch of random items to be done LATER

        World world = player.getWorld(); //Get the current world that the player is in
        Location location = droppedItem.getLocation(); //Get the location of dropped item

        //Spawn 1 diamond
        ItemStack item = new ItemStack(Material.DIAMOND, 1); // Spawn one singular diamond

        world.dropItemNaturally(location, item); //Should drop the item
    }
}

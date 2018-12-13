package site.modicum.rgbf;

/**
 AUTHOR: IGGYSAUR
 DATE: 29/10/2018
 FOR: RETRIBUTION GAMING - WILD WEST FRONTIER
 VERSION 1.0
 */

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class RGBF extends JavaPlugin implements Listener {

    private static RGBF INSTANCE;

    @Override
    public void onEnable() {

        INSTANCE = this;
        getInstance().getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        getCommand("rgbf").setExecutor(new cmdRGBF());
        getServer().getPluginManager().registerEvents(INSTANCE, INSTANCE); }


    @EventHandler
    private void onBlockLeftClick(PlayerInteractEvent event){

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) && isOnList(event.getClickedBlock().getTypeId()))
        {
            event.getClickedBlock().breakNaturally();
        }
    }

    protected boolean isOnList(int blockID){
        return getConfig().getIntegerList("blocksToIgnoreWorldGuard").contains(blockID);
    }

    protected boolean addToList(int blockID){

        if (!isOnList(blockID))
        {
            List<Integer> blockList = getConfig().getIntegerList("blocksToIgnoreWorldGuard");
            blockList.add(blockID);
            getConfig().set("blocksToIgnoreWorldGuard", blockList);
            saveConfig();
            return true;
        }
        else return false;
    }

    protected boolean removeFromList(int blockID){

        if (isOnList(blockID))
        {
            List<Integer> blockList = getConfig().getIntegerList("blocksToIgnoreWorldGuard");
            blockList.remove(new Integer(blockID));
            getConfig().set("blocksToIgnoreWorldGuard", blockList);
            saveConfig();
            return true;
        }
        else return false;
    }


    protected static RGBF getInstance()
    {
        return INSTANCE;
    }

    @Override
    public void onDisable() { }

}

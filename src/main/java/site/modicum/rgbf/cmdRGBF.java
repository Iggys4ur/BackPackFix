package site.modicum.rgbf;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class cmdRGBF implements CommandExecutor {

    private RGBF plugin = RGBF.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 1 || !(sender instanceof Player) || !sender.hasPermission("rgbf.admin")) {
            return false;
        }

        Player player = (Player) sender;
        int blockID = player.getItemInHand().getTypeId();
        char arg = args[0].toLowerCase().charAt(0);

        switch (arg) {

            default: return false;

            case 'a':
                if (plugin.addToList(blockID)) {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§2 added");
                    player.sendMessage("§e       - Config list updated.");
                } else {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§2 is already registered §fon the list.");
                }
                break;

            case 'r':
                if (plugin.removeFromList(blockID)) {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§4 removed");
                    player.sendMessage("§e       - Config list updated.");
                } else {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§4 is *NOT* registered §fon the list.");
                }
                break;

            case 'c':
                if (plugin.isOnList(blockID)) {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§2 is registered §fon the list.");
                } else {
                    player.sendMessage("");
                    player.sendMessage("§a[RGBF] §fBlockID: §3" + blockID + "§4 is *NOT* registered §fon the list.");
                }
                break;
        }

        return true;
    }
}

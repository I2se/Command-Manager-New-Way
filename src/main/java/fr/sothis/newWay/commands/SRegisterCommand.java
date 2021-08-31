package fr.novlab.api.commands;

import fr.novlab.api.FoxAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SRegisterCommand extends Command implements PluginIdentifiableCommand {

    protected SRegisterCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        Map<SRegisterCommand, SCommand> linkedCommand = CommandManager.Instance.getLinkRegister();
        SCommand sCommand = linkedCommand.get(this);
        sCommand.execute(new SothisExecutor(commandSender, s, Arrays.asList(strings)));
        return false;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {

        return super.tabComplete(sender, alias, args);
    }

    @Override
    public Plugin getPlugin() {
        return FoxAPI.Instance;
    }
}

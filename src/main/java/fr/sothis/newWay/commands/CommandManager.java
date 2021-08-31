package fr.novlab.api.commands;

import fr.novlab.api.FoxAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {

    public static final CommandManager Instance = new CommandManager();
    public Map<SRegisterCommand, SCommand> linkRegister = new HashMap<>();
    public List<SCommand> commands = new ArrayList<>();

    public void registerCommands() {
        addCommand(new SCommand("eco", "A Command For Manage Economy", "/eco <subcommands>", "fox.eco", "economy", "economie")
                .addSubCommand(new SCommand("add")
                        .dynamicArgument(""))
                .addSubCommand(new SCommand("remove"))
                .addSubCommand(new SCommand("reset"))
                .addSubCommand(new SCommand("set")));
    }

    public void addCommand(SCommand command) {
        commands.add(command);
        SRegisterCommand sRegisterCommand = new SRegisterCommand(command.getCommandInfo().getName(), command.getCommandInfo().getDescription(), command.getCommandInfo().getUsage(), command.getCommandInfo().getAliases());
        linkRegister.put(sRegisterCommand, command);
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(FoxAPI.Instance.getDescription().getName().toLowerCase(), sRegisterCommand);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Map<SRegisterCommand, SCommand> getLinkRegister() {
        return linkRegister;
    }

    public List<SCommand> getCommands() {
        return commands;
    }
}

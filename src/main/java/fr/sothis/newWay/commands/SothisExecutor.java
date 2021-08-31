package fr.novlab.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SothisExecutor {

    private CommandSender sender;
    private String label;
    private List<String> args;

    public SothisExecutor(CommandSender sender, String label, List<String> args) {
        this.sender = sender;
        this.label = label;
        this.args = args;
    }

    public CommandSender getSender() {
        return sender;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getArgs() {
        return args;
    }

    public String getFirstArgs() {
        return args.get(0);
    }

    public String getSecondArgs() {
        return args.get(1);
    }

    public String getThirdArgs() {
        return args.get(2);
    }

    public String getArgAtPosition(int i) {
        return args.get(i - 1);
    }
}

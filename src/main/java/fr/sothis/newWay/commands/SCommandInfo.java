package fr.novlab.api.commands;

import java.util.List;

public class SCommandInfo  {

    private String name;
    private String description;
    private String usage;
    private List<String> aliases;
    private List<String> permissions;
    private SCommand sCommand;

    public SCommandInfo(String name, String description, String usage, List<String> aliases, List<String> permissions, SCommand sCommand) {
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.aliases = aliases;
        this.permissions = permissions;
        this.sCommand = sCommand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public SCommand getsCommand() {
        return sCommand;
    }

    public void setsCommand(SCommand sCommand) {
        this.sCommand = sCommand;
    }
}

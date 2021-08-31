package fr.novlab.api.commands;

import org.bukkit.ChatColor;

import java.util.*;
import java.util.function.Consumer;

public class SCommand {

    private SCommandInfo sCommandInfo;
    private List<SCommand> subCommands = new ArrayList<>();
    private Map<String, Consumer<SothisExecutor>> linkedConsumer;
    private Consumer<SothisExecutor> dynamicConsumer;
    private List<String> customCompletion = new ArrayList<>();
    private String errorMessage;

    protected SCommand(String name, String description, String usageMessage, String permissions, String... aliases) {
        this.sCommandInfo = new SCommandInfo(name, description, usageMessage, Arrays.asList(aliases), Collections.singletonList(permissions), null);
    }

    protected SCommand(String name, String description, String usageMessage, String permissions) {
        this(name, description, usageMessage, permissions,new String[0]);
    }

    protected SCommand(String name, String description, String usageMessage) {
        this(name, description, usageMessage, null, new String[0]);
    }

    protected SCommand(String name, String description) {
        this(name, description, "No Usage", null, new String[0]);
    }

    protected SCommand(String name) {
        this(name, "No Description", "No Usage", null, new String[0]);
    }

    public void execute(SothisExecutor sothisExecutor) {
        if(subCommands != null) {
            List<String> nameOfSubCommands = new ArrayList<>();
            List<String> aliasesOfSubCommands = new ArrayList<>();
            subCommands.forEach(sCommand -> nameOfSubCommands.add(sCommand.getCommandInfo().getName()));
            subCommands.forEach(sCommand -> aliasesOfSubCommands.addAll(sCommand.getCommandInfo().getAliases()));
            if(nameOfSubCommands.contains(sothisExecutor.getLabel()) || aliasesOfSubCommands.contains(sothisExecutor.getLabel())) {
                for (SCommand subCommand : subCommands) {
                    if(subCommand.getCommandInfo().getName().equals(sothisExecutor.getFirstArgs()) || subCommand.getCommandInfo().getAliases().contains(sothisExecutor.getFirstArgs())) {
                        subCommand.execute(sothisExecutor);
                    }
                }
            } else {
                executeArgument(sothisExecutor);
            }
        } else {
            executeArgument(sothisExecutor);
        }
    }

    private void executeArgument(SothisExecutor sothisExecutor) {
        if(linkedConsumer != null) {
            if(linkedConsumer.containsKey(sothisExecutor.getFirstArgs())) {
                Consumer<SothisExecutor> consumer = linkedConsumer.get(sothisExecutor.getFirstArgs());
                consumer.accept(sothisExecutor);
            } else {
                executionFinal(sothisExecutor);
            }
        } else {
            executionFinal(sothisExecutor);
        }
    }

    private void executionFinal(SothisExecutor sothisExecutor) {
        if(dynamicConsumer != null) {
            dynamicConsumer.accept(sothisExecutor);
        } else {
            if(errorMessage == null) {
                errorMessage = ChatColor.RED + "Unknown Command";
            }
            sothisExecutor.getSender().sendMessage(errorMessage);
        }
    }

    public SCommand onArgument(String arg, Consumer<SothisExecutor> consumer) {
        this.linkedConsumer.put(arg, consumer);
        return this;
    }

    public SCommand dynamicArgument(Consumer<SothisExecutor> consumer) {
        this.dynamicConsumer = consumer;
        return this;
    }

    public SCommand addTabCompletion(String... strings) {
        this.customCompletion.addAll(Arrays.asList(strings));
        return this;
    }

    public SCommand setErrorMessage(String message) {
        this.errorMessage = message;
        return this;
    }

    public SCommand addSubCommand(SCommand sCommand) {
        this.subCommands.add(sCommand);
        sCommand.sCommandInfo.setsCommand(this);
        return this;
    }

    public SCommand setName(String name) {
        this.sCommandInfo.setName(name);
        return this;
    }

    public SCommand setDescription(String description) {
        this.sCommandInfo.setDescription(description);
        return this;
    }

    public SCommand setUsage(String usage) {
        this.sCommandInfo.setUsage(usage);
        return this;
    }

    public SCommand setAliases(String... aliases) {
        this.sCommandInfo.setAliases(Arrays.asList(aliases));
        return this;
    }

    public SCommand setPermissions(String... permissions) {
        this.sCommandInfo.setPermissions(Arrays.asList(permissions));
        return this;
    }

    public SCommandInfo getCommandInfo() {
        return this.sCommandInfo;
    }

    public List<String> getCustomCompletion() {
        return customCompletion;
    }

    public List<SCommand> getSubCommands() {
        return subCommands;
    }

    public Map<String, Consumer<SothisExecutor>> getLinkedConsumer() {
        return linkedConsumer;
    }
}

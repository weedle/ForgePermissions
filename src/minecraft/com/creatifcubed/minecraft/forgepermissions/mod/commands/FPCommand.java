package com.creatifcubed.minecraft.forgepermissions.mod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.common.ConfigCategory;

public class FPCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "fp";
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 2; // 0 for anyone (needs canCommandSenderUseCommand to override), 2 for admin, 4 for server
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
		return super.canCommandSenderUseCommand(par1ICommandSender);
	}

	@Override
	public void processCommand(ICommandSender sender, String[] params) {
		// TODO Auto-generated method stub		
	}
}
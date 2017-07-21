
package net.jason.eastland.mods;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Chat;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.jason.eastland.mods.KeyboardSimulator;

import java.awt.AWTException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.impl.client.TargetAuthenticationStrategy;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.player.EntityPlayer;

public class ElmodMain {
	@SubscribeEvent
	/**
	 * 验证码处理程序
	 * 如果聊天中包含验证码，那么提出验证码放到聊天栏中
	 * @param event
	 * @return
	 * @throws AWTException
	 * @throws IOException
	 */
	public String dealvarifycode(ClientChatReceivedEvent event) throws AWTException, IOException {
		KeyboardSimulator mykey = new KeyboardSimulator();
		//System.out.println("getvaryifycode active ");
		ITextComponent itxt = event.getMessage();
		String string = itxt.getFormattedText();
		//System.out.println("get chat string:"+string); 
		String regCode = "验证码.*([A-Z]{4})";
		Pattern regPattern = Pattern.compile(regCode);
		Matcher matcher = regPattern.matcher(string);

		if (matcher.find()){	
			String code = matcher.group(1);
			mykey.setString(code);
			mykey.start();
			mykey.sendCode2MCchatbar(code);
			mykey.stopThread();
			return code;	
		}
		else {
			return "";
		}
	}
}
	
	


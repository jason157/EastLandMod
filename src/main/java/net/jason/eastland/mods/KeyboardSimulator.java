package net.jason.eastland.mods;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.ibm.icu.text.Normalizer.Mode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.fml.common.Mod;
import scala.tools.nsc.doc.model.Public;
import scala.tools.nsc.interpreter.ISettings;


public class KeyboardSimulator extends Thread {
	// 键盘映射表
	public KeyboardSimulator(){
		HashMap map = new HashMap();
		map.put("A",65);
		map.put("B",66);
		map.put("C",67);
		map.put("D",68);
		map.put("E",69);
		map.put("F",70);
		map.put("G",71);
		map.put("H",72);
		map.put("I",73);
		map.put("J",74);
		map.put("K",75);
		map.put("L",76);
		map.put("M",77);
		map.put("N",78);
		map.put("O",79);
		map.put("P",80);
		map.put("Q",81);
		map.put("R",82);
		map.put("S",83);
		map.put("T",84);
		map.put("U",85);
		map.put("V",86);
		map.put("W",87);
		map.put("X",88);
		map.put("Y",89);
		map.put("Z",90);
				
	}
	
	String inputsting = "";
	boolean flag = true;
	//重写run，多线程
	@Override
	public void run() {		
		while (flag) {
			try {
				sendMsg2MCchatbar(this.inputsting);
				this.flag=false;
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void setString(String string) {
		this.inputsting = string;	
	}
	
	public void stopThread() {
		boolean flag = false;
		
	}
	
	//发送消息到MC的聊天栏总，带模式
	public void sendMsg2MCchatbar(String string,int mode) throws AWTException{
		
		Robot robot = new Robot(); 
		
		if (mode == 0&& string.length()==1) {
			keyPress(robot, KeyEvent.VK_T);  
		}
		else if (mode == 1 && string.length()!=1){
			keyPress(robot, KeyEvent.VK_T);  
			keyPressString(robot, string); 
		}
		else {
			
		}
	}
	public void sendMsg2MCchatbar(String string) throws AWTException{
		Robot robot = new Robot(); 
		Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡ���а�
		keyPress(robot, KeyEvent.VK_T);  
		robot.delay(100);        
		//keyPressString(robot, string); 
        Transferable tText = new StringSelection(string);
        clip.setContents(tText, null); 
        //keyPressWithCtrl(robot, KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(100); 
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(100); 
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(100); 
        robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(500);       
		keyPress(robot, KeyEvent.VK_ENTER);
	}
	
	
	public void sendCode2MCchatbar(String string) throws AWTException, IOException{
		Robot robot = new Robot(); 
		
		GuiChat myGuiChat = new GuiChat();
		Keyboard.enableRepeatEvents(true);
		//keyPress(robot, KeyEvent.VK_T);  
		myGuiChat.handleKeyboardInput();
		//System.out.println("handleKeyboardInput");
		//int tryNum = 0;
		/*
		while(!myGuiChat.||tryNum <5){
			keyPress(robot, KeyEvent.VK_T);
			tryNum=tryNum+1;	
		}
		*/
		onekeyPress(string);
		System.out.println("press code success");
		keyPress(robot, KeyEvent.VK_ENTER);
		System.out.println("OVER");
	}
    // shift+ ����
    public static void keyPressWithShift(Robot r, int key) {
            r.keyPress(KeyEvent.VK_SHIFT);
            r.keyPress(key);
            r.keyRelease(key);
            r.keyRelease(KeyEvent.VK_SHIFT);
            r.delay(100);
    }

    // ctrl+ ����
    public static void keyPressWithCtrl(Robot r, int key) {
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(key);
            r.keyRelease(key);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.delay(100);
    }

    // alt+ ����
    public static void keyPressWithAlt(Robot r, int key) {
            r.keyPress(KeyEvent.VK_ALT);
            r.keyPress(key);
            r.keyRelease(key);
            r.keyRelease(KeyEvent.VK_ALT);
            r.delay(100);
    }
    //��ӡ���ַ���
    public static void keyPressString(Robot r, String str){
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();//��ȡ���а�
            Transferable tText = new StringSelection(str);
            clip.setContents(tText, null); //���ü��а�����
            keyPressWithCtrl(r, KeyEvent.VK_V);//ճ��
            r.delay(100);
    }
    
    //���� ����
    public static void keyPress(Robot r,int key){
            r.keyPress(key);
            r.keyRelease(key);
            r.delay(100);
    }
    
    //��ȡ��ĸ�����ֱ𵥸������д
    public static void onekeyPress(String string) throws AWTException {
    	Robot robot = new Robot(); //����һ��robot����
    	char[] chars = string.toCharArray();
    	for(int i=0;i<chars.length;i++){
    		keyPressWithShift(robot, Integer.valueOf(chars[i]));
    		robot.delay(100);
    		}	
	}

}

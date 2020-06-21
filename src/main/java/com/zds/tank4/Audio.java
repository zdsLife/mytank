package com.zds.tank4;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
//import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
public class Audio extends Thread {
    private AudioFormat audioFormat = null;
    private SourceDataLine sourceDataLine = null;
    private DataLine.Info dataLine_info = null;

    private AudioInputStream audioInputStream = null;
    @Override
    public void run() {
        try {
            //准备好字节数组
            byte[] b= new byte[1024];
            //为读声音io作长度准备
            int len = 0;
            sourceDataLine.open(audioFormat,1024);
            sourceDataLine.start();
            while ((len=audioInputStream.read(b))>0){
                sourceDataLine.write(b,0,len);
            }
            audioInputStream.close();
            sourceDataLine.drain();
            sourceDataLine.close();
         }catch (Exception e) {

         }finally {

        }
    }

    public Audio(String fileName) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            //FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            //volctrl.setValue(-40);//

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

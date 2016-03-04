package copy;


import java.io.*;
import javax.sound.sampled.*;

public class AudioFileProcessor {

  public static void main(String[] args) {
	  //Give the location of wav file here along with destination folder
    copyAudio("F:\\a\\walk.wav","F:\\a\\", 5);
    //copyAudio("F:\\a\\ABC.flac","F:\\a\\", 5);
  }

  public static void copyAudio(String sourceFileName, String destinationFileName, int secondsToCopy) {
    AudioInputStream inputStream = null;
    AudioInputStream shortenedStream = null;
    try {
      File file = new File(sourceFileName);
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      System.out.println(fileFormat);
      AudioFormat format = fileFormat.getFormat();
      //System.out.println(format);
      long audioFileLength = file.length();
      //System.out.println(audioFileLength);
      
      
      inputStream = AudioSystem.getAudioInputStream(file);
      long frames = inputStream.getFrameLength();
      double durationInSeconds = (frames+0.0) / format.getFrameRate();
      System.out.println(durationInSeconds);
      int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
      System.out.println(bytesPerSecond);
      String DestFileName;
      int startSecond=0;
      int a=(int) (durationInSeconds/5);
      for(int i=0;i<=a;i++){
    	  
      //inputStream.skip(startSecond * bytesPerSecond);
      long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
      shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
      DestFileName=destinationFileName+Integer.toString(i)+".wav";
      File destinationFile = new File(DestFileName);
      AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
      }
    
    
    } catch (Exception e) {
      println(e);
    } finally {
      if (inputStream != null) try { inputStream.close(); } catch (Exception e) { println(e); }
      if (shortenedStream != null) try { shortenedStream.close(); } catch (Exception e) { println(e); }
    }
  }

  public static void println(Object o) {
    System.out.println(o);
  }

  public static void print(Object o) {
    System.out.print(o);
  }

}
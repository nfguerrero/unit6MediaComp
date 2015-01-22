import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set only blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to set only red */
  public void keepOnlyRed()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to negate the picture */
  public void negate()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              Color newColor = new Color((255 - pixels[row][col].getRed()),
                                         (255 - pixels[row][col].getGreen()),
                                         (255 - pixels[row][col].getBlue()));
              pixels[row][col].setColor(newColor);
          }
      }
  }
  
  /** Method to make the picture a grayscale */
  public void grayscale()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              int red = pixels[row][col].getRed();
              int green = pixels[row][col].getGreen();
              int blue = pixels[row][col].getBlue();
              int gray = (red + green + blue)/3;
              Color color = new Color(gray, gray, gray);
              pixels[row][col].setColor(color);
          }
      }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from right to leftt */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from top to bottom */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        botPixel = pixels[pixels.length - 1 - row][col];
        botPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  
  /** Method that mirrors the picture around a 
    * horizontal mirror in the center of the picture
    * from bottom to top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length / 2; row++)
    {
      for (int col = 0; col < width; col++)
      {
        topPixel = pixels[row][col];
        botPixel = pixels[pixels.length - 1 - row][col];
        topPixel.setColor(botPixel.getColor());
      }
    } 
  }
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** Mirror just the arms of the snowman */
  public void mirrorArms()
  {
      int mirrorPoint = 194;
      Pixel topPixel = null;
      Pixel botPixel = null;
      Pixel[][] pixels = this.getPixels2D();
      //Left Arm
      for (int row = 163; row < mirrorPoint; row++)
      {
          for (int col = 105; col < 172; col++)
          {
              topPixel = pixels[row][col];
              botPixel = pixels[mirrorPoint + mirrorPoint - row][col];
              botPixel.setColor(topPixel.getColor());
          }
      }
      //Right Arm
      for (int row = 163; row < mirrorPoint; row++)
      {
          for (int col = 237; col < 294; col++)
          {
              topPixel = pixels[row][col];
              botPixel = pixels[mirrorPoint + mirrorPoint - row][col];
              botPixel.setColor(topPixel.getColor());
          }
      }
  }
  
  /** Mirror just the seagull to make two */
  public void mirrorGull()
  {
      int mirrorPoint = 344;
      Pixel oldPixel = null;
      Pixel newPixel = null;
      Pixel[][] pixels = this.getPixels2D();
      
      for (int row = 233; row < 318; row++)
      {
          for (int col = 238; col < mirrorPoint; col++)
          {
              oldPixel = pixels[row][col];
              newPixel = pixels[row + 10][col + 110];
              newPixel.setColor(oldPixel.getColor());
          }
      }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  /** Method to reverse image horizontally */
  public void reverseHorizontal(Picture pic)
  {
      Pixel[][] oldPixels = this.getPixels2D();
      Pixel[][] newPixels = pic.getPixels2D();

      for (int row = 0; row < oldPixels.length; row++)
      {
          for (int col = 0; col < oldPixels[0].length; col++)
          {
              oldPixels[row][oldPixels[0].length-col-1].setColor(newPixels[row][col].getColor());
          }
      }
  }
  
  /** Method to reverse image vertically */
  public void reverseVertical(Picture pic)
  {
      Pixel[][] oldPixels = this.getPixels2D();
      Pixel[][] newPixels = pic.getPixels2D();

      for (int row = 0; row < oldPixels.length; row++)
      {
          for (int col = 0; col < oldPixels[0].length; col++)
          {
              oldPixels[oldPixels.length-row-1][col].setColor(newPixels[row][col].getColor());
          }
      }
  }
  
  /** Method to make two headed lamb */
  public void twoHead()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 7; row < 167; row++)
      {
          int colCount = 0;
          for (int col = 157; col < 256; col++)
          {
              pixels[row][157-colCount].setColor(pixels[row][col].getColor());
              colCount++;
          }
      }
  }
  
  /** Method to make each pixel randomly zero in either r, g, or b */
  public void randomZero()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          for (int col = 0; col < pixels[0].length; col++)
          {
              double rand = Math.random()*3;
              if (rand < 1)
              {
                  pixels[row][col].setRed(0);
              }
              else if (rand < 2)
              {
                  pixels[row][col].setGreen(0);
              }
              else
              {
                  pixels[row][col].setBlue(0);
              }
          }
      }
  }
  
  /**Method to create a gradient blue */
  public void gradientBlue()
  {
      Pixel[][] pixels = this.getPixels2D();
      for (int row = 0; row < pixels.length; row++)
      {
          double count = 0;
          for (int col = 0; col < pixels[0].length; col++)
          {
              pixels[row][col].setBlue((int)(255*(count/pixels[0].length)));
              count++;
          }
      }
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture sourcePic = new Picture("JumpingLamb.jpg");
    this.copy(sourcePic,0,0);
    Pixel[][] picPixels = sourcePic.getPixels2D();
    
    Picture picture = new Picture("JumpingLamb.jpg");
    picture.reverseVertical(sourcePic);
    picture.gradientBlue();
    this.copy(picture,276,0);
    
    Picture test1 = new Picture("JumpingLamb.jpg");
    Picture test2 = new Picture("JumpingLamb.jpg");
    test2.reverseHorizontal(test1);
    
    Picture pic1 = new Picture("JumpingLamb.jpg");
    pic1.mirrorVerticalRightToLeft();
    pic1.mirrorHorizontal();
    this.copy(pic1,0,460);
    
    Picture pic2 = new Picture("JumpingLamb.jpg");
    pic2.reverseHorizontal(test1);
    pic2.twoHead();
    this.copy(pic2,0,920);
    
    Picture pic3 = new Picture("JumpingLamb.jpg");
    pic3.reverseVertical(test1);
    pic3.mirrorVerticalRightToLeft();
    this.copy(pic3,276,460);
    
    Picture pic4 = new Picture("JumpingLamb.jpg");
    pic4.reverseVertical(test2);
    pic4.randomZero();
    this.copy(pic4,276,920);
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /** Method to crop and copy a picture
   * @param     Picture sourcePicture
   * @param     int startSourceRow
   * @param     int endSourceRow
   * @param     int startSourceCol
   * @param     int endSourceCol
   * @param     int startDestRow
   * @param     int startDestCol
   */
  public void cropAndCopy(Picture sourcePicture, int startSourceRow, int endSourceRow, 
                            int startSourceCol, int endSourceCol, int startDestRow, int startDestCol)
  {
      Pixel[][] pic1 = sourcePicture.getPixels2D();
      Pixel[][] pic2 = this.getPixels2D(); 
      int addRow = 0;
      for (int row = startSourceRow; row < endSourceRow; row++)
      {
          int addCol = 0;
          for (int col = startSourceCol; col < endSourceCol; col++)
          {
              pic2[startDestRow+addRow][startDestCol+addCol].setColor(pic1[row][col].getColor());
              pic1[row][col].setColor(new Color(255, 255, 255));
              addCol++;
          }
          addRow++;
      }
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this

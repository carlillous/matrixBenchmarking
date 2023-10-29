package matrix.reader;

import matrix.compresor.Coordinate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatrixReader {

    public Coordinate readerToCoordinate(String filePath){

        Coordinate mco = new Coordinate();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern numberPattern = Pattern.compile("-?\\d+");
            int count = 0;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("%")) {
                    continue;
                }else{
                    if(count == 1){
                        Matcher matcher = numberPattern.matcher(line);
                        List<Integer> numbers = new ArrayList<>();
                        while (matcher.find()) {
                            int num = Integer.parseInt(matcher.group());
                            numbers.add(num);
                        }
                        mco.add(numbers.get(0),numbers.get(1), numbers.get(2).doubleValue());
                }else{
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mco;

    }

}

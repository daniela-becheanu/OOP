import utils.Game;
import com.fasterxml.jackson.databind.ObjectMapper;

import input.Input;
import output.End;
import output.Output;
import java.io.File;
public final class Main {

    private Main() { }
    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);

        Game game = Game.getInstance();
        game.start(input);

        End end = End.getInstance();
        Output output = end.gameEnd(input);

        ObjectMapper objectMapperOut = new ObjectMapper();
        objectMapperOut.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);
    }
}

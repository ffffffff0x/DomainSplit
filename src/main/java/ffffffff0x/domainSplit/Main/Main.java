package ffffffff0x.domainSplit.Main;

import sun.awt.windows.WPrinterJob;

/**
 * @author: RyuZUSUNC
 * @create: 2021-06-03 10:34
 **/

public class Main {
    public static void main(String[] args) {
        CliController cliController = new CliController();
        try {
            if (cliController.isReady(args[0],args[2])){
                cliController.run(args[1],args[3]);
            }
        }catch (Exception e){
            cliController.waring();
        }

    }
}

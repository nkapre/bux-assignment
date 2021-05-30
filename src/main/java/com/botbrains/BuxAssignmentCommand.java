package com.botbrains;

import com.botbrains.cbo.trading.TradeParameters;
import com.botbrains.trading.TradeOperator;
import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;

/**
 * Entry point into the application that starts the whole process and also take care of the validations
 * of the parameters.
 */
@Command(name = "bux-assignment", description = "...", mixinStandardHelpOptions = true)
public class BuxAssignmentCommand implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(BuxAssignmentCommand.class);

    //@CommandLine.Spec
    protected CommandLine.Model.CommandSpec spec;

    private String productId;
    private String buyPrice;
    private String upperSellPrice;
    private String lowerSellPrice;

    @Inject
    TradeOperator botRunner;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(BuxAssignmentCommand.class, args);
    }

    @Option(names = {"--productId"},
            description = "Product ID")
    public void setProductId(String productId) {
        if (StringUtils.isEmpty(productId)) {
            throw new CommandLine.ParameterException(
                    spec.commandLine(),
                    "productId cannot be left empty.");
        }
        this.productId = productId;
    }

    @Option(names = {"--buyPrice"},
            description = "Buying price")
    public void setBuyPrice(String buyPrice) {
        if (StringUtils.isEmpty(buyPrice)) {
            throw new CommandLine.ParameterException(
                    spec.commandLine(), "buyPrice cannot be left empty.");
        }
        this.buyPrice = buyPrice;
    }

    @Option(names = {"--upperSellPrice"},
            description = "Upper selling price")
    public void setUpperSellPrice(String upperSellPrice) {
        if (StringUtils.isEmpty(upperSellPrice)) {
            throw new CommandLine.ParameterException(
                    spec.commandLine(), "upperSellPrice cannot be left empty.");
        }
        this.upperSellPrice = upperSellPrice;
    }

    @Option(names = {"--lowerSellPrice"},
            description = "Lower selling price")
    public void setLowerSellPrice(String lowerSellPrice) {
        if (StringUtils.isEmpty(lowerSellPrice)) {
            throw new CommandLine.ParameterException(
                    spec.commandLine(), "lowerSellPrice cannot be left empty.");
        }
        this.lowerSellPrice = lowerSellPrice;
    }

    public void run() {

        TradeParameters params = new TradeParameters(productId, buyPrice, upperSellPrice, lowerSellPrice);
        validateStartupParameters(params);
        botRunner.start(params);
    }

    private static void validateStartupParameters(TradeParameters params) {
        String productId = params.getProductId();
        String buyPrice = params.getBuyPrice();
        String upperSellPrice = params.getUpperLimitSellPrice();
        String lowerSellPrice = params.getLowerLimitSellPrice();

        if(StringUtils.isEmpty(productId) || StringUtils.isEmpty(buyPrice) || StringUtils.isEmpty(upperSellPrice) ||
                StringUtils.isEmpty(lowerSellPrice)){
            LOG.error("None of the startup parameters can be lesser than zero (0) and upper limit sell price cannot be " +
                    "lesser than lower limit sell price");
            System.exit(1);
        }

        if (Double.parseDouble(upperSellPrice) < Double.parseDouble(lowerSellPrice)) {
            LOG.error("None of the startup parameters can be lesser than zero (0) and upper limit sell price cannot be " +
                    "lesser than lower limit sell price");
            System.exit(1);
        }

    }

}

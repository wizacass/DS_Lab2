package edu.ktu.ds.lab2.utils;

/**
 * @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra
 * <p>
 * Tai yra  interfeisas, kurį turi tenkinti KTU studentų kuriamos duomenų klasės
 * Metodai užtikrina patogų duomenų suformavimą iš String eilučių
 ******************************************************************************/
public interface Parsable<T> extends Comparable<T>
{
    /**
     * Suformuoja objektą iš eilutės
     *
     * @param e dataString
     */
    void parse(String e);
}

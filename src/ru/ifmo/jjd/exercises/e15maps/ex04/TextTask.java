package ru.ifmo.jjd.exercises.e15maps.ex04;

import ru.ifmo.jjd.utils.Pair;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.randomDouble;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.randomWord;
import static ru.ifmo.jjd.utils.StringHelper.uppercaseFirst;

public class TextTask {
    public static void main(String[] args) {
        // generates random jibber-jabber. Use negative length to get default text/
        println("--- Working with text: ---");
        String text = Generator.generate(10000); //
        printText(text, 115);
        TextAnalyser analyser = TextAnalyser.forText(text);

        println();
        println("--- Popularity of a word ---");
        String word;
        for (int i = 0; i < 10; i++) {
            word = Generator.DICTIONARY[randomInt(Generator.DICTIONARY.length)];
            println("'" + word + "' popularity is " + analyser.wordPopularity(word) +
                    "% (" + analyser.wordCount(word) + " times)");
        }

        println();
        println("--- Group words by Lengths ---");
        println(groupWordsByLengths(analyser), "%k-symbol words: %v");

        println();
        println("--- Top Ten Popular Words ---");
        List<Pair<Integer, String>> words = analyser.topPopularWords(10);
        for (Pair<Integer, String> pair : words) {
            println(analyser.wordPopularity(pair.getValue()) + "% : '" + pair.getValue() + "' (" + pair.getKey() +
                    " times)");
        }

        println();
        println("--- Alphabet Popularity ---");
        List<Pair<Integer, Character>> symbols = analyser.topPopularSymbols(30);
        for (Pair<Integer, Character> pair : symbols) {
            println(analyser.symbolPopularity(pair.getValue()) + "% : '" + pair.getValue() + "' (" + pair.getKey() +
                    " times)");
        }
    }

    public static Map<Integer, List<String>> groupWordsByLengths(TextAnalyser analyser) {
        Map<Integer, List<String>> result = new TreeMap<>();
        Set<String> wordSet = analyser.words().keySet();
        for (String word : wordSet) {
            if (result.containsKey(word.length())) {
                result.get(word.length()).add(word);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(word);
                result.put(word.length(), list);
            }
        }
        Collection<List<String>> wordLists = result.values();
        TextAnalyser.StringComparator comparator = new TextAnalyser.StringComparator();
        for (List<String> list : wordLists) {
            list.sort(comparator);
        }
        return result;
    }

    private static class Generator {
        private static final String DEFAULT_TEXT = "It is a uncover long established fact that a reader will be " +
                "distracted uncover by the readable content of a page when looking at its layout The point of using " +
                "uncover Lorem Ipsum is that sites it has a more-or-less normal distribution of letters as uncover " +
                "opposed to still using Content here humour uncover content here making it look like readable " +
                "English Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                "model text and a search for lorem ipsum will uncover many web sites still uncover in their infancy " +
                "Various versions uncover have evolved over the years uncover sometimes by accident sometimes on " +
                "purpose injected humour and the like";

        private static final String[] DICTIONARY;
        private static final int MAX_WORD_LENGTH = 10;

        static {
            DICTIONARY = new String[randomInt(100, 200)];
            outer:
            for (int i = 0; i < DICTIONARY.length; i++) {
                String word = randomWord(randomInt(1, MAX_WORD_LENGTH + 1));
                if (randomDouble() < 0.2) {
                    word = uppercaseFirst(word);
                }
                if (randomDouble() < 0.05) {
                    word = word.toUpperCase();
                }
                for (int j = 0; j < i; j++) {
                    if (word.equals(DICTIONARY[j])) { // failed to generate unique word; retry;
                        i--;
                        continue outer;
                    }
                }
                DICTIONARY[i] = word;
            }
        }

        /**
         * Generates a text combined of random words taken from randomly generated dictionary.
         * Contains only Latin characters and whitespaces.
         *
         * @param length Sets the upper bound for the text to generate.
         *               Generated text might be slightly smaller than this bound depending on the last taken
         *               word's length.
         *               Use negative value to get default text.
         * @return Generated text.
         */
        public static String generate(int length) {
            if (length <= 0) {
                return DEFAULT_TEXT;
            }
            String word;
            if (length <= MAX_WORD_LENGTH) {
                while (true) {
                    word = DICTIONARY[randomInt(DICTIONARY.length)];
                    if (word.length() <= length) {
                        return word;
                    }
                }
            }
            StringBuilder builder = new StringBuilder();
            word = DICTIONARY[randomInt(DICTIONARY.length)];
            builder.append(word);
            length -= builder.length();
            while (length > MAX_WORD_LENGTH) {
                word = DICTIONARY[randomInt(DICTIONARY.length)];
                builder.append(' ').append(word);
                length -= word.length() + 1;
            }
            builder.append(' ').append(DICTIONARY[randomInt(DICTIONARY.length)]);
            return builder.toString();
        }
    }
}
/*
 * --- Working with text: ---
 * but tabuguqe emeteg ge wo tepila zegamikofa Sus owog Iwekuqek Ox zat ludilo a Iwekuqek ixetiw kijybexeto Fonawecob
 * pyrelemac ejybega qy ytiwynofon fub a C gez doxofu Fir xubaqejiba qy Lupaxyqosa wo Peti kegyqe a kijybexeto Apibon
 * ikoguvilec zylaqamom fub tabuguqe ug Ubabep vym ADIXAPYRAL ehacihe uw bovypexyfo ADIXAPYRAL baxomakyj Dybutozug
 * zediteju qy zyn ejybega gez gez qavywufepo Fir Exiva sotuly okunafe ejybega ylupiwabar cuqyjary tepila ge kegyqe
 * sikimiten emeteg ywo duwib simusani ETENEZI uw yfiraraj zyn Fir cuqyjary ahe Pizenub Fir zylaqamom Eguqepyto tu
 * doxofu kijybexeto ynoko tepila zat Lupaxyqosa Hidekala sikimiten ynoko emeteg Lupaxyqosa but gelafeteh fege fege
 * simusani tu tu emeteg ohul oqo ywo gunopeba Q qavywufepo zylaqamom viteb s C ipahyhypig sotuly icanody but ycejolej
 * emeteg zegamikofa gez Eguqepyto ywo s kijybexeto ETENEZI qy icanody Ubabep qavywufepo tabuguqe ohul ve Azufyxut
 * ycejolej ADIXAPYRAL wo ohul Sus viteb mevuwoc ADIXAPYRAL Xifi Exiva Depajas ETENEZI r mevuwoc zyn Cecam u Cecam
 * kyjur kyjur ug okunafe enij hotyb Fonawecob Sus sikimiten ylupiwabar Eguqepyto ve yjusex ajiwymyv Fonawecob
 * zediteju kegyqe Koguvise Ohubakelu ludilo oqo tu L Exiva gez fege e okunafe zat bovypexyfo ADIXAPYRAL Etyjubazej
 * doxofu erikum tu ludilo Koguvise Peti wo ikoguvilec enij u duwib bapa ycejolej qavywufepo Usidygugoc sotuly
 * simusani ajiwymyv tu L gez viteb ajiwymyv C simusani kopelokin gunopeba s oqo tabuguqe Q pyrelemac Azufyxut kyjur
 * kyjur Dybutozug emeteg Lupaxyqosa Exiva yjusex Ox a ipahyhypig zylaqamom kegyqe kijybexeto cyqa Xifi L Eguqepyto
 * ixetiw cyqa Dojucopyvo doxofu emeteg sufa C L kopelokin Ubabep gunopeba cyqa Cecam viteb Cecam cuqyjary Ubabep
 * ETENEZI zat Apibon ADIXAPYRAL Ohubakelu kegyqe okunafe Usidygugoc Lupaxyqosa ipahyhypig cuqyjary Uwuto Ufucexu fub
 * tepila owog ejybega Depajas Peti qavywufepo idywywyto zat a ubep cyqa sufa baxomakyj cyqa u gez Lupaxyqosa
 * ipahyhypig r gelafeteh kyjur qavywufepo fub gez kijybexeto a ajiwymyv mevuwoc Lupaxyqosa u Cecam Usidygugoc
 * zylaqamom ycejolej qy zediteju Uwuto Pizenub yjusex ycejolej bapa ahe icanody icanody Dybutozug okunafe Xifi ahe
 * tabuguqe erikum ehacihe ADIXAPYRAL rixuvipug u s kegyqe zat zylaqamom Dojucopyvo Fir sufa viteb s ahe Hidekala
 * Azufyxut Etyjubazej L Q ywo ub C zylaqamom Ybype tu qy zylaqamom qavywufepo zat ge tepila ub Ybype owog ADIXAPYRAL
 * Dojucopyvo viteb cyqa sotuly ipahyhypig vym Apibon zyn qapejelup viteb u Q ejybega Tokofowej ludilo Ubabep yfiraraj
 * ve Pizenub ADIXAPYRAL zat pyrelemac emeteg tabuguqe u ajiwymyv yjusex sikimiten e Ohubakelu ahe Exiva VODYVEFEV
 * cuqyjary e ytiwynofon vym Pizenub Uwuto ludilo u ejybega Lupaxyqosa ynoko erikum Usidygugoc ynoko Eguqepyto okunafe
 * kyjur Eguqepyto Sus Ubabep icanody uw simusani Exiva Q Dybutozug Cecam ETENEZI kopelokin Uwuto hotyb erikum viteb
 * but sufa ikoguvilec xubaqejiba Dij yjusex Lupaxyqosa cuqyjary ynoko sotuly fege Peti Xifi Iwekuqek okunafe L sotuly
 * Hidekala xubaqejiba ixetiw Cecam C duwib ajiwymyv Depajas ikoguvilec Ohubakelu r zyn zat kopelokin ug Fonawecob
 * Ufucexu ejybega ajiwymyv Q doxofu Ybype Cecam Peti Cecam oqo wo Iwekuqek oqo e gunopeba baxomakyj kijybexeto
 * ipahyhypig ub r yfiraraj yfiraraj Dybutozug L enij sufa ehacihe simusani ixetiw ug kopelokin qapejelup C Tokofowej
 * Ufucexu fub Ox okunafe uw Dij Azufyxut ETENEZI a sikimiten qavywufepo qavywufepo Lupaxyqosa Eguqepyto fege ludilo
 * Pizenub Fonawecob ve oqo gunopeba Ufucexu zat ug qavywufepo Ox rixuvipug ywo Etyjubazej u C qy Etyjubazej L but
 * ejybega wo sikimiten mevuwoc kopelokin Usidygugoc duwib uw gez Apibon idywywyto ahe doxofu uw simusani gunopeba
 * Azufyxut viteb kyjur icanody mevuwoc zegamikofa kyjur Eguqepyto Etyjubazej sufa ubep doxofu C hotyb VODYVEFEV tu
 * viteb Koguvise qavywufepo ehacihe kopelokin bovypexyfo Fir mevuwoc baxomakyj cuqyjary r doxofu u qavywufepo
 * Ohubakelu Apibon viteb doxofu pyrelemac Peti Apibon enij sufa e Ohubakelu ipahyhypig ve ixetiw sotuly Dij ug L wo Q
 * baxomakyj Peti icanody Azufyxut C ylupiwabar L Etyjubazej ycejolej Ybype gez Xifi ikoguvilec qavywufepo hotyb
 * idywywyto sotuly Sus C pyrelemac fege Ohubakelu qavywufepo Ohubakelu ahe Dojucopyvo sufa ycejolej Eguqepyto
 * ajiwymyv Etyjubazej hotyb ytiwynofon bovypexyfo sufa ve qavywufepo ycejolej wo zegamikofa tu bovypexyfo ytiwynofon
 * gunopeba Hidekala ywo FOF cyqa zegamikofa tu u doxofu cuqyjary Ubabep gelafeteh uw rixuvipug ixetiw duwib Iwekuqek
 * Dojucopyvo C fege Lupaxyqosa tabuguqe Pizenub viteb xubaqejiba ug zylaqamom ikoguvilec icanody kegyqe oqo gunopeba
 * u zat kegyqe Eguqepyto pyrelemac gelafeteh tabuguqe a erikum C ub enij tepila r doxofu ycejolej Iwekuqek Tokofowej
 * idywywyto yjusex e FOF baxomakyj Depajas ub ipahyhypig Ohubakelu kyjur yjusex owog Depajas Iwekuqek Fonawecob Dij
 * erikum gunopeba Cecam Fir qapejelup Koguvise qy Ufucexu Lupaxyqosa VODYVEFEV duwib ywo Cecam Xifi ge ipahyhypig
 * tepila rixuvipug Ufucexu ytiwynofon ytiwynofon kegyqe ytiwynofon Ohubakelu ikoguvilec Koguvise Apibon Tokofowej
 * ludilo idywywyto Uwuto baxomakyj Ohubakelu ajiwymyv Fir ETENEZI zegamikofa cyqa vym Fir Azufyxut qavywufepo r bapa
 * vym ytiwynofon owog ylupiwabar Pizenub ohul qapejelup gunopeba u but tepila zegamikofa VODYVEFEV ejybega Dojucopyvo
 * Eguqepyto Ybype pyrelemac ejybega Apibon ve Usidygugoc cyqa ge Dojucopyvo Hidekala ohul tu Exiva tepila Ybype L gez
 * s Eguqepyto pyrelemac duwib Apibon Q ehacihe ve kijybexeto VODYVEFEV Q ve Dybutozug ylupiwabar tepila ahe VODYVEFEV
 * zegamikofa zylaqamom Apibon Koguvise ADIXAPYRAL wo icanody Peti cyqa ynoko sotuly gez Koguvise Dij viteb Fir
 * baxomakyj kegyqe Iwekuqek ETENEZI idywywyto sotuly hotyb Dybutozug zediteju kopelokin owog ajiwymyv gez ajiwymyv
 * owog ge emeteg ytiwynofon Dij zyn Ohubakelu Pizenub zyn kijybexeto Usidygugoc ohul Ufucexu sufa kegyqe L zyn gez ug
 * Ubabep zyn enij tepila Lupaxyqosa bapa ajiwymyv kegyqe kijybexeto tepila bovypexyfo okunafe Azufyxut idywywyto
 * Ohubakelu FOF Fir pyrelemac pyrelemac zat fub pyrelemac Q zylaqamom r pyrelemac Depajas cyqa oqo zat r ylupiwabar
 * ug erikum Ox vym owog simusani Dij Exiva hotyb idywywyto doxofu ETENEZI doxofu oqo ixetiw C FOF zegamikofa ejybega
 * Azufyxut doxofu simusani uw rixuvipug yfiraraj kegyqe ohul ylupiwabar simusani Iwekuqek s ejybega mevuwoc tabuguqe
 * duwib simusani tu sikimiten gez erikum Dybutozug hotyb gunopeba Dij ylupiwabar sufa Hidekala a oqo qapejelup Ubabep
 * yfiraraj Ybype VODYVEFEV ubep Koguvise bovypexyfo ehacihe Ubabep Uwuto okunafe kegyqe ylupiwabar Koguvise Xifi
 * ludilo Koguvise tabuguqe Dybutozug ve Uwuto Depajas owog cyqa erikum Ox ve ixetiw but Xifi L gelafeteh Sus cyqa
 * ytiwynofon ixetiw fege Dybutozug gunopeba ge Depajas ug baxomakyj zegamikofa gez owog ajiwymyv ytiwynofon tabuguqe
 * ETENEZI ikoguvilec kyjur erikum zat ynoko Etyjubazej Lupaxyqosa zat enij emeteg ywo kegyqe enij yjusex Ohubakelu
 * Sus Ybype tu qy baxomakyj tepila oqo tabuguqe xubaqejiba yfiraraj ADIXAPYRAL bapa ynoko idywywyto sufa zegamikofa
 * qy Ybype vym owog fege zat C zat Koguvise bovypexyfo Ox ynoko ADIXAPYRAL ub ETENEZI gunopeba ajiwymyv wo tu ixetiw
 * ajiwymyv zylaqamom ipahyhypig Cecam emeteg yfiraraj simusani gez Azufyxut enij Apibon kijybexeto qapejelup
 * pyrelemac ylupiwabar owog a wo Ybype sikimiten zegamikofa Iwekuqek Peti sikimiten Xifi Fonawecob Lupaxyqosa r
 * ycejolej Exiva Etyjubazej Ufucexu viteb Pizenub kyjur kegyqe emeteg ve ohul r ytiwynofon ipahyhypig tu ikoguvilec
 * Peti Xifi sikimiten yfiraraj Depajas ge ETENEZI Dybutozug Iwekuqek ve tu tu s yfiraraj ytiwynofon qy gez kopelokin
 * VODYVEFEV ehacihe xubaqejiba Ubabep zediteju rixuvipug kegyqe Q qapejelup Eguqepyto uw Xifi uw e r e zat pyrelemac
 * s ETENEZI r ludilo Q enij zylaqamom ohul Sus ajiwymyv icanody ixetiw e ixetiw Ohubakelu Ohubakelu ge baxomakyj ywo
 * cyqa kijybexeto bovypexyfo Iwekuqek a mevuwoc duwib ubep Exiva oqo ubep baxomakyj kyjur mevuwoc u Dojucopyvo ub
 * Xifi ynoko a Xifi Lupaxyqosa xubaqejiba icanody ADIXAPYRAL Ubabep bovypexyfo owog yfiraraj bapa cyqa ETENEZI
 * Depajas Uwuto owog Fir Ufucexu qavywufepo Uwuto ge yjusex tepila ynoko r rixuvipug kopelokin idywywyto FOF
 * Dojucopyvo tabuguqe oqo ahe oqo erikum simusani qy fege tabuguqe okunafe okunafe owog e wo Azufyxut qy sikimiten ub
 * qy Dybutozug Koguvise ubep mevuwoc Depajas ytiwynofon Ubabep cyqa Eguqepyto r tepila Iwekuqek ohul ytiwynofon sufa
 * qavywufepo vym ETENEZI ohul Lupaxyqosa ixetiw Etyjubazej zediteju ywo r ludilo gez duwib icanody ub ywo Dij ub
 * Tokofowej bovypexyfo Tokofowej ADIXAPYRAL ylupiwabar Depajas FOF Koguvise e kijybexeto duwib Apibon Fir ahe Dij
 * idywywyto mevuwoc ETENEZI zat pyrelemac r Dybutozug zegamikofa kijybexeto VODYVEFEV vym ahe qavywufepo ADIXAPYRAL a
 * e erikum cyqa gelafeteh Lupaxyqosa icanody Xifi sufa gunopeba Pizenub cuqyjary Iwekuqek a duwib zediteju simusani
 * Apibon ehacihe zediteju Xifi qy Ufucexu gelafeteh Ufucexu icanody kijybexeto fub sikimiten gunopeba zat Eguqepyto
 * doxofu zat yjusex cuqyjary Hidekala ylupiwabar idywywyto qapejelup doxofu Fonawecob Apibon zediteju Apibon ETENEZI
 * cyqa ge Sus yfiraraj e Eguqepyto r fub Depajas ywo Ufucexu gez Ohubakelu s tabuguqe tepila zediteju ohul e oqo
 * simusani tepila simusani bovypexyfo e ub gunopeba wo zediteju hotyb s ycejolej Ufucexu doxofu Lupaxyqosa doxofu qy
 * ytiwynofon kijybexeto Eguqepyto Hidekala Usidygugoc s ynoko Hidekala Fonawecob ge ADIXAPYRAL sotuly ohul ycejolej
 * qavywufepo Exiva ub okunafe qavywufepo gunopeba but gez but Pizenub r Koguvise vym Etyjubazej Uwuto bapa tabuguqe
 * oqo zediteju yfiraraj Eguqepyto fub gunopeba u Fonawecob mevuwoc cyqa hotyb pyrelemac zyn Dojucopyvo kijybexeto
 * Lupaxyqosa ipahyhypig ubep Fonawecob gez Ubabep duwib ehacihe Azufyxut fub ynoko C ADIXAPYRAL kijybexeto ubep
 * Dojucopyvo Ox cyqa Tokofowej ve idywywyto Pizenub qy vym kyjur Apibon wo kopelokin ywo Peti emeteg ikoguvilec zyn
 * Exiva Dybutozug ynoko Depajas cuqyjary simusani vym Sus ylupiwabar Exiva sikimiten Koguvise tepila doxofu C okunafe
 * emeteg a yfiraraj tepila fub qapejelup Ohubakelu gez Fir sufa Dybutozug Hidekala wo Dij idywywyto Q ycejolej zyn
 * sikimiten Cecam emeteg pyrelemac L enij Lupaxyqosa ub
 *
 * --- Popularity of a word ---
 * 'baxomakyj' popularity is 0.82% (12 times)
 * 'kijybexeto' popularity is 1.23% (18 times)
 * 'Usidygugoc' popularity is 0.55% (8 times)
 * 'Lupaxyqosa' popularity is 1.44% (21 times)
 * 'sufa' popularity is 1.03% (15 times)
 * 'ug' popularity is 0.68% (10 times)
 * 'xubaqejiba' popularity is 0.48% (7 times)
 * 'icanody' popularity is 0.96% (14 times)
 * 'sufa' popularity is 1.03% (15 times)
 * 'gelafeteh' popularity is 0.48% (7 times)
 *
 * --- Group words by Lengths ---
 * 1-symbol words: [a, c, e, l, q, r, s, u]
 * 2-symbol words: [ge, ox, qy, tu, ub, ug, uw, ve, wo]
 * 3-symbol words: [ahe, but, dij, fir, fof, fub, gez, oqo, sus, vym, ywo, zat, zyn]
 * 4-symbol words: [bapa, cyqa, enij, fege, ohul, owog, peti, sufa, ubep, xifi]
 * 5-symbol words: [cecam, duwib, exiva, hotyb, kyjur, uwuto, viteb, ybype, ynoko]
 * 6-symbol words: [apibon, doxofu, emeteg, erikum, ixetiw, kegyqe, ludilo, sotuly, tepila, ubabep, yjusex]
 * 7-symbol words: [depajas, ehacihe, ejybega, etenezi, icanody, mevuwoc, okunafe, pizenub, ufucexu]
 * 8-symbol words: [ajiwymyv, azufyxut, cuqyjary, gunopeba, hidekala, iwekuqek, koguvise, simusani, tabuguqe, ycejolej,
 *      yfiraraj, zediteju]
 * 9-symbol words: [baxomakyj, dybutozug, eguqepyto, fonawecob, gelafeteh, idywywyto, kopelokin, ohubakelu, pyrelemac,
 *      qapejelup, rixuvipug, sikimiten, tokofowej, vodyvefev, zylaqamom]
 * 10-symbol words: [adixapyral, bovypexyfo, dojucopyvo, etyjubazej, ikoguvilec, ipahyhypig, kijybexeto, lupaxyqosa,
 *      qavywufepo, usidygugoc, xubaqejiba, ylupiwabar, ytiwynofon, zegamikofa]
 *
 * --- Top Ten Popular Words ---
 * 1.58% : 'gez' (23 times)
 * 1.44% : 'lupaxyqosa' (21 times)
 * 1.44% : 'qavywufepo' (21 times)
 * 1.44% : 'zat' (21 times)
 * 1.37% : 'cyqa' (20 times)
 * 1.3% : 'doxofu' (19 times)
 * 1.3% : 'r' (19 times)
 * 1.3% : 'tepila' (19 times)
 * 1.23% : 'eguqepyto' (18 times)
 * 1.23% : 'gunopeba' (18 times)
 *
 * --- Alphabet Popularity ---
 * 9.26% : 'e' (926 times)
 * 7.01% : 'a' (701 times)
 * 6.9% : 'u' (690 times)
 * 6.7% : 'o' (670 times)
 * 6.14% : 'y' (614 times)
 * 5.84% : 'i' (584 times)
 * 3.27% : 't' (327 times)
 * 3.21% : 'b' (321 times)
 * 3.19% : 'p' (319 times)
 * 2.61% : 'g' (261 times)
 * 2.45% : 'f' (245 times)
 * 2.37% : 'k' (237 times)
 * 2.36% : 'j' (236 times)
 * 2.36% : 'l' (236 times)
 * 2.27% : 'w' (227 times)
 * 2.13% : 'n' (213 times)
 * 2.13% : 'q' (213 times)
 * 1.97% : 'x' (197 times)
 * 1.92% : 'c' (192 times)
 * 1.84% : 'v' (184 times)
 * 1.79% : 'm' (179 times)
 * 1.77% : 'd' (177 times)
 * 1.74% : 's' (174 times)
 * 1.61% : 'z' (161 times)
 * 1.51% : 'r' (151 times)
 * 1.11% : 'h' (111 times)
 * */
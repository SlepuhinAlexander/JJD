package ru.ifmo.jjd.exercises.e20lambdas.ex06;

import ru.ifmo.jjd.utils.Pair;

import java.util.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.randomDouble;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.randomWord;
import static ru.ifmo.jjd.utils.StringHelper.uppercaseFirst;

public class TextTask {
    public static void main(String[] args) {
        println("--- Working with text: ---");
        String text = Generator.generate(10000); //
        printText(text, 115);
        TextAnalyser analyser = TextAnalyser.forText(text);

        println();
        println("--- Popularity of a word ---");
        String word;
        for (int i = 0; i < 10; i++) {
            word = Generator.dictionary[randomInt(Generator.dictionary.length)];
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
            printf("%.2f%% : '%s' (%d times)\n",
                    analyser.wordPopularity(pair.getValue()),
                    pair.getValue(),
                    pair.getKey());
        }

        println();
        println("--- Alphabet Popularity ---");
        List<Pair<Integer, Character>> symbols = analyser.topPopularSymbols(30);
        for (Pair<Integer, Character> pair : symbols) {
            printf("%.2f%% : '%s' (%d times)\n",
                    (analyser.symbolPopularity(pair.getValue())),
                    pair.getValue(),
                    pair.getKey());
        }
    }

    private static Map<Integer, List<String>> groupWordsByLengths(TextAnalyser analyser) {
        Map<Integer, List<String>> result = new TreeMap<>();
        analyser.getWords().forEach((word, count) -> result.merge(word.length(),
                new ArrayList<>(Collections.singletonList(word)),
                (words, words2) -> {
                    words.add(word);
                    return words;
                }));
        return result;
    }

    static class Generator {
        static String[] dictionary;

        static {
            int dictionarySize = randomInt(100, 200);
            Set<String> words = new HashSet<>();
            String word;
            while (words.size() < dictionarySize) {
                word = randomWord(randomInt(1, 11));
                if (randomDouble() < 0.2) uppercaseFirst(word);
                if (randomDouble() < 0.05) word = word.toUpperCase();
                words.add(word);
            }
            dictionary = new String[dictionarySize];
            words.toArray(dictionary);
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
                return "It is a uncover long established fact that a reader will be distracted uncover by the " +
                       "readable content of a page when looking at its layout The point of using uncover Lorem Ipsum " +
                       "is that sites it has a more-or-less normal distribution of letters as uncover opposed to " +
                       "still using Content here humour uncover content here making it look like readable English " +
                       "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                       "model text and a search for lorem ipsum will uncover many web sites still uncover in their " +
                       "infancy Various versions uncover have evolved over the years uncover sometimes by accident " +
                       "sometimes on purpose injected humour and the like";
            }
            String word;
            if (length <= 10) {
                while (true) {
                    word = dictionary[randomInt(dictionary.length)];
                    if (word.length() <= length) return word;
                }
            }
            StringBuilder builder = new StringBuilder();
            word = dictionary[randomInt(dictionary.length)];
            builder.append(word);
            length -= builder.length();
            while (length > 0) {
                word = dictionary[randomInt(dictionary.length)];
                builder.append(' ').append(word);
                length -= word.length() + 1;
            }
            builder.setLength(builder.length() - 1);
            return builder.toString();
        }
    }
}
/*
 * --- Working with text: ---
 * nexoceruc wof jasacedosy jasacedosy nugafobyb rilaqo ami ozy kupugohul iwobufak t ami posucip S rilaqo agavugoci
 * dusewoxa wof etemiv ujusyj ire guja elufilopyq ybutiw uvu imymyqosu s elufilopyq ihulamuco kahibefom efudazahad
 * yfycy kahibefom xudiqocule nek yl guja padelybo ikywa jazi pawem m wofowinyja ew qafuhypu ywofil demopypi kahibefom
 * i myxuzapyce yheqa uzaf habif zuwoba iwobufak m jazi ok cib ybutiw j ofej laf AZIXIPASY wanemocuw ikyjab j ujusyj
 * wof YJEP habif guja wofowinyja e xuvysih AZIXIPASY IZUB izihybica axi igigag b jeqycozez habif dakivo elufilopyq
 * abewog uqav vytamad odeti gysecetome tahujom r ybutiw rydasoro FUCAPA bazumu AZIXIPASY gysecetome wofowinyja
 * ewatiso dasipom uzaf abewog ozy rilaqo ybumupi elufilopyq nek r upo qavexyfiw padelybo rilaqo en wegom jisubify
 * rydasoro obexupi b wanemocuw a m fodi rydasoro gytu ejave ava padelybo uhihy elufilopyq ihulamuco te umokobonuf
 * bazumu esyvuc padelybo ol yqa efudazahad acynysypyf uzubicoh m uvu QO rilaqo beqade u ol guze ek yhucomuc ka
 * obexupi jiryb ah hur s vytamad minurit ekiqyr icuduzod qumis guze UZAS IZUB te kupugohul tahoq dakivo anucijer ol
 * osokirunu a k yheqa poveciqin yhucomuc vytamad yheqa posucip o dasipom nome beqade equ ejularon gytu ofej
 * wofowinyja beqade my d i nugafobyb ato bego izihybica ok agavugoci bole yheqa ozy ekov yj MIMIKUFACE UZAS uzaf o
 * wof uzubicoh ek qavexyfiw ocabi ajaxyk bole fodi ybumupi uzubicoh gytu wanemocuw ofej fodi gixyd a uzubicoh uxufa
 * duratugu laf dakivo poveciqin imymyqosu edy tahujom duratugu moryjuwire ekov kuqyvip moryjuwire YRIGIFAW asoweromy
 * IZUB beqade bole dasipom ywo dasipom dyroqy MIMIKUFACE uba cem d yqobuji ybumupi uzubicoh kac habif molobycep
 * ujusyj laf gixyd rydasoro habif ava wegom k wof S s kahibefom QO obekuxa bazumu fodi rydasoro ekov ewatiso QO
 * ajaxyk a gixyd ajaxyk ka a uba kohatix enyp cem exunuv yfycy jazi otinowip enyp acynysypyf YRIGIFAW bole duratugu
 * gixyd esyvuc wof zuwoba ok t pawem qavexyfiw ek axi rilaqo yqobuji enyp r liwyfahobu tahujom ekov xuvysih u
 * ihulamuco izihybica exunuv v ywo habif ka yz ag wolu ijaca dasipom ka ah xuvysih YJEP dusewoxa yce upo exunuv bole
 * oq myxuzapyce dasipom wanemocuw ejularon ol moryjuwire bego r t ehanajyga b gytu suloriciwu te beqade enyp UZAS
 * ehanajyga ew ijaca guja moryjuwire xuvysih guze nexoceruc beqade jeqycozez uba uxufa i wegom zuwoba demopypi ato
 * nome igigag ok molobycep nugafobyb YRIGIFAW posucip s obekuxa guja r icuduzod bego minurit s nek ok qucejuhos
 * minurit beqade wanemocuw ek tahujom nexoceruc QO padelybo uba kuqyvip dasipom vojexot ekiqyr y quhicazugo minurit
 * qavexyfiw jisubify h otinowip yl okakax hur ujusyj nugafobyb dyroqy UZAS gixyd s nome jisubify upo vojexot iwobufak
 * ijaca umokobonuf o MIMIKUFACE hur fodi ol i bazumu uzaf IN gixyd obexupi wegom r enyp yqa kuqyvip olyd hur vojexot
 * izihybica ijaca oxa uzaf y a IN ocabi esyvuc uzubicoh ekiqyr ujusyj ywofil abewog vojexot ozy efudazahad ury
 * udegyjih pawem YJEP jasacedosy kohilukeve poveciqin igigag minurit gytu j fodi tahoq exunuv wof quhicazugo qumis
 * cib xuvysih jasacedosy u hur xuvysih dakivo ava molobycep ek nexoceruc aqehysy agavugoci kuqyvip molobycep ekov yz
 * kac padelybo bole joj wof kohilukeve jeqycozez hur etemiv aqehysy yfycy gytu ok m h ato jazi umokobonuf wof yce
 * qavexyfiw jeqycozez ybutiw uxufa uba laf m nek asoweromy uzubicoh tahujom equ ava r kohatix uzubicoh efudazahad yqa
 * j umokobonuf tahujom jeqycozez igigag hur ejularon nyr qucejuhos cem ava moryjuwire a yvozivy ek ejor elufilopyq
 * kupugohul xudiqocule ava agavugoci kupugohul posucip ava vojexot ag izihybica enyp kohilukeve anucijer efudazahad
 * UZAS equ kupugohul qumis uhihy udegyjih ury duratugu yheqa ekiqyr FUCAPA anucijer dakivo ury nugafobyb liwyfahobu
 * imymyqosu yheqa kirocih moryjuwire ybutiw uzubicoh ato abewog gixyd ehanajyga odeti nek v ew igigag yqa a
 * jasacedosy ok IZUB osokirunu yce obekuxa QO qavexyfiw okamutenaq uhihy nek ejave ew ybutiw ury enyp abewog odeti
 * efudazahad ikyjab anucijer igigag etemiv nome poveciqin m UZAS equ jasacedosy yqa poveciqin qucejuhos oq ofej IN
 * laf padelybo anucijer olyd oq MIMIKUFACE ah ejave guja ihulamuco rilaqo joj en guze yfycy ka ah rydasoro ami
 * dusewoxa yvozivy bole bazumu yfycy qavexyfiw S yfycy enyp vytamad gixyd uba jasacedosy ihulamuco guze nyr kohatix
 * kac equ wofowinyja yj oq jisubify efudazahad kuqyvip iwobufak quhicazugo jasacedosy ejularon jazi ava iwobufak
 * kahibefom tahujom jeqycozez yl UZAS agavugoci cem ywo r ol imymyqosu ikyjab suloriciwu abewog yfycy yheqa ury
 * kohilukeve elufilopyq en S guze QO demopypi wolu esyvuc ozy cem ikyjab moryjuwire cem wegom ejave jazi okamutenaq
 * kac te otinowip liwyfahobu ejor dusewoxa habif qumis xudiqocule cib guja cib acynysypyf duratugu ek wolu yz ag yqa
 * ezur d te liwyfahobu bego qafuhypu s ocabi ag liwyfahobu jisubify wanemocuw rydasoro ozy r wolu posucip vojexot
 * tahujom udegyjih myxuzapyce kohatix beqade ezur moryjuwire uhihy edy imymyqosu myxuzapyce gytu ire r h ol edy nek
 * nek umokobonuf yl yl agavugoci kac ka QO osokirunu anucijer kirocih beqade kahibefom demopypi o UZAS ekov poveciqin
 * ewatiso gysecetome otinowip a gysecetome uqav nek tahujom YRIGIFAW quhicazugo UZAS yz en ihulamuco r uzubicoh gytu
 * s xuvysih olyd ekiqyr MIMIKUFACE otinowip v e xuvysih okamutenaq b k yheqa m ywo ehanajyga kohilukeve ozy joj yfycy
 * o otinowip enyp nexoceruc pawem qavexyfiw ihulamuco yce nugafobyb nugafobyb a iwobufak uqav ire en ajaxyk IZUB
 * ywofil te tahujom ami qucejuhos moryjuwire pawem umokobonuf YRIGIFAW y ewatiso joj xuvysih nek e ek ihulamuco
 * wofowinyja yqobuji lu kohatix yqobuji minurit anucijer duratugu qavexyfiw dusewoxa ujusyj ujusyj wegom s guja
 * ejularon S okamutenaq wolu gysecetome h demopypi ujusyj te j dasipom hur xuvysih aqehysy padelybo yz habif
 * jasacedosy IN habif ywo ami aqehysy IZUB ok my MIMIKUFACE duratugu cem m equ otinowip oxa wanemocuw ka moryjuwire
 * UZAS S h ag fodi IZUB osokirunu habif demopypi quhicazugo UZAS yqobuji agavugoci izihybica wanemocuw ury cem etemiv
 * a nugafobyb ajaxyk h uqav s moryjuwire elufilopyq uhihy ywofil nome ihulamuco yqobuji qumis jazi ybumupi otinowip
 * acynysypyf S esyvuc ah axi zuwoba pawem ikywa posucip ato lu ekiqyr jeqycozez nugafobyb ato jiryb suloriciwu cib
 * dakivo my j ah uhihy demopypi poveciqin joj IN nek d dakivo jisubify dyroqy j exunuv enyp osokirunu ato i j
 * jasacedosy bazumu wegom umokobonuf ewatiso laf qavexyfiw ava moryjuwire a ozy ew udegyjih joj kupugohul obekuxa ka
 * guze ejor ihulamuco yheqa kohilukeve ag IN suloriciwu laf icuduzod ag joj ewatiso gytu asoweromy wegom igigag
 * udegyjih ajaxyk kohilukeve otinowip molobycep otinowip o joj j ihulamuco ek dyroqy ijaca ejularon ah S obekuxa
 * aqehysy myxuzapyce elufilopyq yqobuji o ekiqyr ywo xudiqocule qumis zuwoba yhucomuc en m s yhucomuc esyvuc
 * umokobonuf wofowinyja obexupi ujusyj bole kohatix igigag IN jazi iwobufak ywofil izihybica equ ah YJEP suloriciwu
 * ejularon yheqa nome QO ajaxyk ywo qumis pawem oxa kohatix odeti upo osokirunu ek xuvysih vytamad elufilopyq
 * moryjuwire yce ikywa jisubify uxufa qucejuhos xudiqocule oq equ yvozivy ezur d kahibefom qumis j ka k cib uzubicoh
 * iwobufak liwyfahobu lu yhucomuc laf kuqyvip kahibefom h yj y qumis dasipom nome enyp te ew axi d beqade poveciqin
 * esyvuc ezur uzubicoh myxuzapyce wegom liwyfahobu jeqycozez okakax wof y d t MIMIKUFACE bazumu ihulamuco ekiqyr
 * duratugu liwyfahobu qavexyfiw ybutiw yheqa ikyjab molobycep umokobonuf pawem ywofil jiryb dusewoxa ybumupi e i nome
 * edy kohilukeve kuqyvip wof jiryb etemiv jeqycozez yqa odeti ujusyj zuwoba IN d wanemocuw qucejuhos kohatix ofej
 * nugafobyb ekiqyr osokirunu yqa s wegom qafuhypu ag posucip ajaxyk qumis YJEP ofej igigag agavugoci qucejuhos
 * icuduzod nugafobyb ewatiso habif nexoceruc axi t dasipom ami AZIXIPASY tahujom efudazahad yce YRIGIFAW v ikywa
 * kohilukeve umokobonuf QO yfycy IN xuvysih jiryb ekiqyr yl guze uzaf joj j minurit te ol yheqa ocabi imymyqosu
 * ehanajyga ikywa gixyd ujusyj bole kupugohul IZUB bazumu upo otinowip habif jeqycozez bego en moryjuwire yhucomuc
 * ekov ah b dasipom padelybo ury poveciqin laf obekuxa okakax ejave liwyfahobu ejularon wof ezur xuvysih jisubify
 * jisubify jeqycozez abewog bego vojexot padelybo etemiv yj ire nugafobyb yhucomuc ejave YJEP yheqa dasipom lu
 * wanemocuw uxufa nexoceruc dakivo obekuxa jasacedosy kupugohul iwobufak S ajaxyk oq kohilukeve quhicazugo S s nome
 * QO ek kohatix olyd uzaf bego poveciqin tahujom ywo anucijer tahujom j oq vojexot my minurit b ava yce uzaf IZUB
 * quhicazugo hur IZUB izihybica ah imymyqosu qavexyfiw joj ybutiw fodi nome qafuhypu anucijer yvozivy ozy ezur uxufa
 * pawem tahoq ol te osokirunu ofej ocabi o ami cem S iwobufak ekiqyr nugafobyb yqa ajaxyk FUCAPA ok xudiqocule ka ah
 * uvu kirocih u rilaqo ek oq lu imymyqosu yqa uqav pawem osokirunu h moryjuwire umokobonuf e asoweromy dyroqy te
 * vojexot hur ire te wegom igigag udegyjih ezur ybumupi ol dakivo kahibefom ury habif uvu equ uvu m d k vytamad a
 * bole tahoq bego axi oxa fodi otinowip okakax nek k kohatix ava s YJEP ol molobycep qumis bazumu ywofil ajaxyk
 * iwobufak uqav j elufilopyq odeti odeti ol duratugu osokirunu aqehysy izihybica uhihy yfycy hur bazumu qavexyfiw ka
 * ew ocabi obexupi obexupi edy kirocih ekiqyr v ato igigag joj AZIXIPASY gixyd laf udegyjih hur poveciqin poveciqin
 * ek ijaca ikyjab d ami qavexyfiw MIMIKUFACE minurit agavugoci iwobufak h igigag tahujom bazumu otinowip xudiqocule
 * odeti dusewoxa v nyr imymyqosu osokirunu yqa umokobonuf xuvysih vojexot qavexyfiw qumis acynysypyf ikywa YJEP wegom
 * guze ybumupi ejor okamutenaq ujusyj jiryb S pawem ywofil my yqa hur UZAS esyvuc kohilukeve okakax S osokirunu
 * posucip ire S YJEP hur gysecetome ezur YJEP uvu jiryb iwobufak u igigag m padelybo yqobuji i uba moryjuwire ewatiso
 * bazumu kohilukeve uqav IN yvozivy gixyd dasipom fodi elufilopyq ag y joj gysecetome nome QO qumis nexoceruc wegom
 * yvozivy nome laf asoweromy icuduzod ewatiso ozy rilaqo vojexot uzubicoh pawem en xudiqocule molobycep suloriciwu
 * kohilukeve hur uzaf rilaqo uvu s bazumu yfycy kohatix dusewoxa ofej bazumu otinowip udegyjih enyp kohatix yfyc
 * --- Popularity of a word ---
 * 'upo' popularity is 0.31% (5 times)
 * 'nek' popularity is 0.76% (12 times)
 * 'ewatiso' popularity is 0.57% (9 times)
 * 'dasipom' popularity is 0.83% (13 times)
 * 'guze' popularity is 0.57% (9 times)
 * 'esyvuc' popularity is 0.51% (8 times)
 * 'MIMIKUFACE' popularity is 0.51% (8 times)
 * 'vytamad' popularity is 0.38% (6 times)
 * 'abewog' popularity is 0.44% (7 times)
 * 'yfycy' popularity is 0.7% (11 times)
 *
 * --- Group words by Lengths ---
 * {1-symbol words: [a, b, d, e, h, i, j, k, m, o, r, s, t, u, v, y]
 *  2-symbol words: [ag, ah, ek, en, ew, in, ka, lu, my, ok, ol, oq, qo, te, yj, yl, yz]
 *  3-symbol words: [ami, ato, ava, axi, cem, cib, edy, equ, hur, ire, joj, kac, laf, nek, nyr, oxa, ozy, uba, upo,
 *      ury, uvu, wof, yce, yqa, ywo]
 *  4-symbol words: [bego, bole, ejor, ekov, enyp, ezur, fodi, guja, guze, gytu, izub, jazi, nome, ofej, olyd, uqav,
 *      uzaf, uzas, wolu, yfyc, yjep]
 *  5-symbol words: [ejave, gixyd, habif, ijaca, ikywa, jiryb, ocabi, odeti, pawem, qumis, tahoq, uhihy, uxufa, wegom,
 *      yfycy, yheqa]
 *  6-symbol words: [abewog, ajaxyk, bazumu, beqade, dakivo, dyroqy, ekiqyr, esyvuc, etemiv, exunuv, fucapa, igigag,
 *      ikyjab, okakax, rilaqo, ujusyj, ybutiw, ywofil, zuwoba]
 *  7-symbol words: [aqehysy, dasipom, ewatiso, kirocih, kohatix, kuqyvip, minurit, obekuxa, obexupi, posucip, tahujom,
 *      vojexot, vytamad, xuvysih, ybumupi, yqobuji, yvozivy]
 *  8-symbol words: [anucijer, demopypi, duratugu, dusewoxa, ejularon, icuduzod, iwobufak, jisubify, otinowip,
 *      padelybo, qafuhypu, rydasoro, udegyjih, uzubicoh, yhucomuc, yrigifaw]
 *  9-symbol words: [agavugoci, asoweromy, azixipasy, ehanajyga, ihulamuco, imymyqosu, izihybica, jeqycozez, kahibefom,
 *      kupugohul, molobycep, nexoceruc, nugafobyb, osokirunu, poveciqin, qavexyfiw, qucejuhos, wanemocuw]
 *  10-symbol words: [acynysypyf, efudazahad, elufilopyq, gysecetome, jasacedosy, kohilukeve, liwyfahobu, mimikuface,
 *      moryjuwire, myxuzapyce, okamutenaq, quhicazugo, suloriciwu, umokobonuf, wofowinyja, xudiqocule]}
 *
 * --- Top Ten Popular Words ---
 * 1.85% : 's' (29 times)
 * 1.02% : 'moryjuwire' (16 times)
 * 0.95% : 'hur' (15 times)
 * 0.95% : 'qavexyfiw' (15 times)
 * 0.89% : 'bazumu' (14 times)
 * 0.89% : 'otinowip' (14 times)
 * 0.89% : 'tahujom' (14 times)
 * 0.89% : 'xuvysih' (14 times)
 * 0.83% : 'a' (13 times)
 * 0.83% : 'dasipom' (13 times)
 *
 * --- Alphabet Popularity ---
 * 7.80% : 'o' (781 times)
 * 7.68% : 'u' (769 times)
 * 7.23% : 'a' (724 times)
 * 6.88% : 'i' (689 times)
 * 6.22% : 'e' (623 times)
 * 5.75% : 'y' (576 times)
 * 2.90% : 'm' (291 times)
 * 2.70% : 'b' (271 times)
 * 2.66% : 'c' (267 times)
 * 2.62% : 'j' (263 times)
 * 2.47% : 'h' (248 times)
 * 2.39% : 'k' (240 times)
 * 2.26% : 'w' (227 times)
 * 2.25% : 's' (226 times)
 * 2.24% : 'q' (225 times)
 * 2.21% : 'f' (222 times)
 * 2.10% : 'r' (211 times)
 * 1.93% : 'g' (194 times)
 * 1.90% : 'n' (191 times)
 * 1.89% : 'p' (190 times)
 * 1.85% : 'd' (186 times)
 * 1.77% : 'l' (178 times)
 * 1.71% : 'z' (172 times)
 * 1.70% : 'v' (171 times)
 * 1.55% : 't' (156 times)
 * 1.47% : 'x' (148 times)
 * */
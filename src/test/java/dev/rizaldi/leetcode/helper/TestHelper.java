package dev.rizaldi.leetcode.helper;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Function;

public class TestHelper {
    private static final Gson gson = new Gson();

    public static File getDirectory(final Class<?> klass) {
        String packageName = klass.getPackage().getName();
        String path = packageName
                .replace("dev.rizaldi.leetcode", "")
                .replaceAll("\\.", "/");
        try {
            URL testURL = Objects.requireNonNull(klass.getResource(path));
            URI testURI = testURL.toURI();
            return new File(testURI);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static <I, O> void run(
            final Function<I, O> process,
            final Class<I> inputClass,
            final Class<O> outputClass,
            final String... caseIds
    ) throws Exception {
        final File directory = getDirectory(inputClass);
        run(process, inputClass, outputClass, directory, caseIds);
    }

    public static <I, O> void run(
            final Function<I, O> process,
            final Class<I> inputClass,
            final Class<O> outputClass,
            final File directory,
            final String... caseIds
    ) throws Exception {
        for (final String caseId : caseIds) {
            final I input = loadInput(inputClass, directory, caseId);
            final O output = loadOutput(outputClass, directory, caseId);
            final O result = process.apply(input);

            final var outputJson = JsonParser.parseString(gson.toJson(output));
            final var resultJson = JsonParser.parseString(gson.toJson(result));
            Assertions.assertEquals(outputJson, resultJson);
        }
    }

    private static <I> I loadInput(
            final Class<I> klass,
            final File directory,
            final String caseId
    ) throws FileNotFoundException {
        final File file = new File(directory, caseId + ".i.json");
        final InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        return gson.fromJson(reader, TypeToken.get(klass));
    }

    private static <O> O loadOutput(
            final Class<O> klass,
            final File directory,
            final String caseId
    ) throws FileNotFoundException {
        final File file = new File(directory, caseId + ".o.json");
        final InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        return gson.fromJson(reader, TypeToken.get(klass));
    }
}

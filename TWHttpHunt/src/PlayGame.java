import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PlayGame {

	static JSONObject stageOne(String string) throws JsonProcessingException, IOException, JSONException {
		int stage = 1;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		JsonNode tree = objectMapper.readTree(string);

		String value = null;
		JSONObject jsonObj = new JSONObject();
		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = tree.fields();
		while (fieldsIterator.hasNext()) {
			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
			value = field.getValue().toString();
			value = value.substring(1, value.length() - 1);

		}
		if (stage == 1) {
			int count = value.length();
			jsonObj.accumulate("count", count);
			System.out.println(jsonObj);
		} else if (stage == 2) {
			String[] wordArray = value.trim().split("\\s+");
			int wordCount = wordArray.length;
			jsonObj.accumulate("wordCount", wordCount);
			System.out.println(jsonObj);
		} else if (stage == 3) {
			int sentensecount = StringUtils.countMatches(value, ".");
			sentensecount = sentensecount + StringUtils.countMatches(value, "?");
			jsonObj.accumulate("sentensecount", sentensecount);
			System.out.println(jsonObj);
		} else if (stage == 4) {
			int a, e, i, o, u;
			a = StringUtils.countMatches(value, "a");
			a = a + StringUtils.countMatches(value, "A");

			e = StringUtils.countMatches(value, "e");
			e = e + StringUtils.countMatches(value, "E");

			i = StringUtils.countMatches(value, "i");
			i = i + StringUtils.countMatches(value, "I");

			o = StringUtils.countMatches(value, "o");
			o = o + StringUtils.countMatches(value, "O");

			u = StringUtils.countMatches(value, "u");
			u = u + StringUtils.countMatches(value, "U");

			jsonObj.accumulate("a", a);
			jsonObj.accumulate("e", e);
			jsonObj.accumulate("i", i);
			jsonObj.accumulate("o", o);
			jsonObj.accumulate("u", u);
			System.out.println(jsonObj);
		}

		return jsonObj;

	}

	static String jsonBeautify(String string) throws IOException, JSONException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		JsonNode tree = objectMapper.readTree(string);
		return objectMapper.writeValueAsString(tree);
	}

}

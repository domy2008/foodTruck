
import cn.hutool.core.date.SystemClock;
import cn.moonshot.platform.util.MoonshotAiUtils;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
enum ChatMessageRole {
    SYSTEM, USER, ASSISTANT;

    public String value() {
        return this.name().toLowerCase();
    }
}

class ChatCompletionMessage {
    public String role;
    public String name;
    public String content;
    public Boolean partial;

    public ChatCompletionMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public ChatCompletionMessage(String role, String name, String content, Boolean partial) {
        this.role = role;
        this.name = name;
        this.content = content;
        this.partial = partial;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Boolean getPartial() {
        return partial;
    }
}

class ChatCompletionStreamChoiceDelta {
    private String content;
    private String role;

    public String getContent() {
        return content;
    }

    public String getRole() {
        return role;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ChatCompletionStreamChoiceDelta(String content, String role) {
        this.content = content;
        this.role = role;
    }
}

class Usage {
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;

    public int getPromptTokens() {
        return promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }
}

class ChatCompletionStreamChoice {
    private int index;
    private ChatCompletionStreamChoiceDelta delta;

    @SerializedName("finish_reason")
    private String finishReason;
    private Usage usage;

    public int getIndex() {
        return index;
    }

    public ChatCompletionStreamChoiceDelta getDelta() {
        return delta;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDelta(ChatCompletionStreamChoiceDelta delta) {
        this.delta = delta;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public ChatCompletionStreamChoice(int index, ChatCompletionStreamChoiceDelta delta, String finishReason, Usage usage) {
        this.index = index;
        this.delta = delta;
        this.finishReason = finishReason;
        this.usage = usage;
    }
}

class ChatCompletionStreamResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChatCompletionStreamChoice> choices;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public List<ChatCompletionStreamChoice> getChoices() {
        return choices;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}

class ChatCompletionChoice {
    private int index;
    private ChatCompletionMessage message;

    @SerializedName("finish_reason")
    private String finishReason;

    public int getIndex() {
        return index;
    }

    public ChatCompletionMessage getMessage() {
        return message;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setMessage(ChatCompletionMessage message) {
        this.message = message;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

}

class ChatCompletionResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<ChatCompletionChoice> choices;
    private Usage usage;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public long getCreated() {
        return created;
    }

    public String getModel() {
        return model;
    }

    public List<ChatCompletionChoice> getChoices() {
        if (choices == null) {
            return List.of();
        }
        return choices;
    }
}


class ChatCompletionRequest {
    public String model;
    public List<ChatCompletionMessage> messages;

    @SerializedName("max_tokens")
    public int maxTokens;

    @SerializedName("temperature")
    public float temperature;
    public float topP;

    public Integer n;
    public boolean stream;
    public List<String> stop;

    @SerializedName("presence_penalty")
    public float presencePenalty;

    @SerializedName("frequency_penalty")
    public float frequencyPenalty;

    public String user;

    public List<ChatCompletionMessage> getMessages() {
        return messages;
    }

    public ChatCompletionRequest(String model, List<ChatCompletionMessage> messages, int maxTokens, float temperature, int n) {
        this.model = model;
        this.messages = messages;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.n = n;
    }

}

class Model {
    private String id;
    private String object;

    @SerializedName("owner_by")
    private String ownedBy;
    private String root;
    private String parent;

    public String getId() {
        return id;
    }

    public String getObject() {
        return object;
    }

    public String getOwnedBy() {
        return ownedBy;
    }

    public String getRoot() {
        return root;
    }

    public String getParent() {
        return parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setOwnedBy(String ownedBy) {
        this.ownedBy = ownedBy;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Model(String id, String object, String ownedBy, String root, String parent) {
        this.id = id;
        this.object = object;
        this.ownedBy = ownedBy;
        this.root = root;
        this.parent = parent;
    }
}

class ModelsList {
    private List<Model> data;

    public List<Model> getData() {
        return data;
    }

    public void setData(List<Model> data) {
        this.data = data;
    }

    public ModelsList(List<Model> data) {
        this.data = data;
    }
}

class Client {
    private static final String DEFAULT_BASE_URL = "https://api.moonshot.cn/v1";

    private static final String CHAT_COMPLETION_SUFFIX = "/chat/completions";
    private static final String MODELS_SUFFIX = "/models";
    private static final String FILES_SUFFIX = "/files";

    private String baseUrl;
    private String apiKey;

    public Client(String apiKey) {
        this(apiKey, DEFAULT_BASE_URL);
    }

    public Client(String apiKey, String baseUrl) {
        this.apiKey = apiKey;
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }
        this.baseUrl = baseUrl;
    }

    public String getChatCompletionUrl() {
        return baseUrl + CHAT_COMPLETION_SUFFIX;
    }

    public String getModelsUrl() {
        return baseUrl + MODELS_SUFFIX;
    }

    public String getFilesUrl() {
        return baseUrl + FILES_SUFFIX;
    }

    public String getApiKey() {
        return apiKey;
    }

    

    public ModelsList ListModels()  {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(getModelsUrl())
                .addHeader("Authorization", "Bearer " + getApiKey())
                .build();
        try (okhttp3.Response response = client.newCall(request).execute()){
           
            String body = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(body, ModelsList.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ChatCompletionResponse ChatCompletion(ChatCompletionRequest request) throws IOException {

        request.stream = false;
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder()
                .readTimeout(0, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, new Gson().toJson(request));
        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url(getChatCompletionUrl())
                .addHeader("Authorization", "Bearer " + getApiKey())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        try( okhttp3.Response response = client.newCall(httpRequest).execute()) {
           
            String responseBody = response.body().string();
            //System.out.println(responseBody);
            Gson gson = new Gson();
            return gson.fromJson(responseBody, ChatCompletionResponse.class);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

   
    
    // return a stream of ChatCompletionStreamResponse
    public Flowable<ChatCompletionStreamResponse> ChatCompletionStream(ChatCompletionRequest request) throws IOException {
        request.stream = true;
       
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder()
                .readTimeout(25, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, new Gson().toJson(request));
        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url(getChatCompletionUrl())
                .addHeader("Authorization", "Bearer " + getApiKey())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        okhttp3.Response response = client.newCall(httpRequest).execute();
        if (response.code() != 200) {
            response.close();
            throw new RuntimeException("Failed to start stream: " + response.body().string());
        }

        // get response body line by line
        Flowable<ChatCompletionStreamResponse> flowable =  Flowable.create(emitter -> {
            okhttp3.ResponseBody responseBody = response.body();
            if (responseBody == null) {
                response.close();
                emitter.onError(new RuntimeException("Response body is null"));
                return;
            }
            String line;
            while ((line = responseBody.source().readUtf8Line()) != null) {
                if (line.startsWith("data:")) {
                    line = line.substring(5);
                    line = line.trim();
                }
                if (Objects.equals(line, "[DONE]")) {
                    emitter.onComplete();
                    return;
                }
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Gson gson = new Gson();
                ChatCompletionStreamResponse streamResponse = gson.fromJson(line, ChatCompletionStreamResponse.class);
                emitter.onNext(streamResponse);
            }
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER);
        return flowable;
    }
}


public class FastWork {

    private static final String schoolPolicyfolder2023 = "/Users/congchang/schoolPolicy/2023AllShanghaiPolicy";
    private static final String schoolPolicyfolder2024 = "/Users/congchang/schoolPolicy/2024AllShanghaiPolicy/";

    /*
     * @description: get all the file content id
     * @param city  city name
     * @param district district name
     * @param schoolType school type
     * 
     * @return List<String> fileContentIDList
     * 
     */
    public static List<String> getAllFileContentID(String city, String district, String schoolPhase) throws Exception{

        //only limit the district, not the shcoolPhase
        if (district.equals("") || schoolPhase.equals("")){
            throw new Exception("Error: district or schoolPhase can not be empty.");
        }
         
        String fileList = MoonshotAiUtils.getFileList();

        //convert fileList to json
        JSONObject jsonObject = new JSONObject(fileList);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<String> fileContentIDList = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject localJson = (JSONObject) object;
            String fileName = localJson.getString("filename");

            //must contain district only
            if (fileName.contains(district) 
                && fileName.contains("2024")
                && fileName.contains(schoolPhase)
                
                ||fileName.contains(district) 
                    && fileName.contains("2024")
                    && fileName.contains("实施意见") ) {
                String fileid = localJson.getString("id");
                fileContentIDList.add(fileid);
            }
        }
        return fileContentIDList;
    }
    public static void deleteAllFiles(){
        String fileList = MoonshotAiUtils.getFileList();

        //convert fileList to json
        JSONObject jsonObject = new JSONObject(fileList);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        for (Object object : jsonArray) {
            JSONObject localJson = (JSONObject) object;
            String fileid = localJson.getString("id");
            MoonshotAiUtils.deleteFile(fileid);
        }
    }
    /**
     * @description: read all the files in a folder and upload them to moonshot
     */
    public static void uploadAllFiles(String folderPath){
        File folder = null;
        try{
             folder = new File(folderPath);
        }catch (Exception e){
            e.printStackTrace();
        }
       
        File[] files = folder.listFiles();
        for (File file : files) {
            String retFile = MoonshotAiUtils.uploadFile(file);

            System.out.println(retFile);
            
        }
    }
    public static void uploadFile(){
        String fString = "/Users/congchang/schoolPolicy/2023AllShanghaiPolicy/2023年嘉定区义务教育阶段初中划片范围、招生方式与招生计划.pdf";
        String retFile = MoonshotAiUtils.uploadFile(new File(fString));
        System.out.println(retFile);
    }
    private static final String assistentContent = """
        你是一个升学顾问，你可以根据上传的文档内容，精确的回答用户关于入学政策的问题。你更加追求根据文档回答问题的精准性，
        在用户第一次提问的时候，你会得到很多文档，你要根据这些文档回答问题，以后的提问，为了节省流量，不会再次上传这些文档，你依旧需要根据这些文档回答问题。
        如果你在文档内容中找不到答案，你会直接告诉用户。而不会自己编造答案。 
        你只会回答用户有关升学政策的问题，不会回答其他问题。你的回答应该是基于文档内容的，而不是你自己的经验或者推测。
        你的用户来自全国各地，因此，每当用户提问的时候，你都会首先问用户，你是哪个城市的，那个区域的，你的孩子要上小学，初中，还是哪个阶段的学校，这样你才能更好的回答用户的问题。
                
        """;
    private static final String forContext = """
            以上的信息和问题，都是历史信息，作为上下文，供你参考，不需要你回答，下面的这个问题，才是用户最新的问题。
            请继续根据第一次上传的文档内容回答问题。
            """;
    private static final String followUpMessage = """
            请继续参照第一次输入的文档内容继续回答，如果没有找到答案，请告诉用户，谢谢！   
            """;
    private static String matchSchoolePhase(String inpuString){
        if (inpuString.contains("高中")|| inpuString.contains("高中阶段")) {
            return "高中";
        }
        if (inpuString.contains("学前")|| inpuString.contains("幼儿")|| inpuString.contains("幼稚")
            ||inpuString.contains("托儿")|| inpuString.contains("托幼")||inpuString.contains("园")){ 
            return "幼儿园";
        }

        if (inpuString.contains("小学")|| inpuString.contains("义务")) {
            return "小学";
        } 
        if (inpuString.contains("初中")) {
            return "初中";
        }
        //System.out.println("Error: can not match school phase.");
        return "";
    }
    
    private static String matchDistrict(String inpuString){
        if (inpuString.contains("杨浦")) {
            return "杨浦区";
        } else if (inpuString.contains("嘉定")) {
            return "嘉定区";
        } else if (inpuString.contains("浦东")) {
            return "浦东区";
        } else if (inpuString.contains("静安")) {
            return "静安区";
        } else if (inpuString.contains("黄浦")) {
            return "黄浦区";
        } else if (inpuString.contains("长宁")) {
            return "长宁区";
        } else if (inpuString.contains("徐汇")) {
            return "徐汇区";
        } else if (inpuString.contains("普陀")) {
            return "普陀区";
        } else if (inpuString.contains("虹口")) {
            return "虹口区";
        } else if (inpuString.contains("松江")) {
            return "松江区";
        } else if (inpuString.contains("闵行")) {
            return "闵行区";
        } else if (inpuString.contains("宝山")) {
            return "宝山区";
        } else if (inpuString.contains("奉贤")) {
            return "奉贤区";
        } else if (inpuString.contains("崇明")) {
            return "崇明区";
        } else if (inpuString.contains("金山")) {
            return "金山区";
        } else if (inpuString.contains("青浦")) {
            return "青浦区";
        }  else if (inpuString.contains("南汇")) {
            return "南汇区";
        } else if (inpuString.contains("闸北")) {
            return "闸北区";
        } else if (inpuString.contains("嘉定")) {
            return "嘉定区";
        } else {
            return "";    
        }
    }


    public static void main(String... args) throws Exception {

        //String schoolPolicyfolder = "/Users/congchang/Desktop/2024kidschool";
        //uploadAllFiles(schoolPolicyfolder);
    
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input your question:");

            String myquestion = scanner.nextLine();
            if (myquestion.equals("exit")) {
                break;
            }
            executeLLM(myquestion);
        }

        scanner.close();
    
    }
    static ChatCompletionResponse response = null;
    public static void syncExecute( List<ChatCompletionMessage> messages, Client client) {
    //    final List<ChatCompletionMessage> messages = List.of(
    //             new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(),
    //             assistentContent),
    //             new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(),
    //             fileContent),
    //             new ChatCompletionMessage(ChatMessageRole.USER.value(),
    //             myquestion)
    //     );

       try {
            response = client.ChatCompletion(new ChatCompletionRequest(
                   "moonshot-v1-128k",
                   messages,
                   1024,
                   0.3f,
                   1
           ));
           for (ChatCompletionChoice choice : response.getChoices()) {
               System.out.println(choice.getMessage().getContent());
           }
           return ;
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    //hold the history questions
    private static ArrayList<String> historyQuestions = new ArrayList<>();
    private static ArrayList<String> historyAnswers = new ArrayList<>();

    private static boolean isFirst = true;

    private static String apiKey = System.getenv("MOONSHOT_API_KEY");

    static Client client = new Client(apiKey);

    public static void executeLLM(String currentQuestion) throws Exception {
        
        String city = "上海市";
        String district = matchDistrict(currentQuestion);

        String schoolPhase = matchSchoolePhase(currentQuestion);

        if (district.equals("")) {
            //search history questions
            for (String historyString : historyQuestions) {
                String dString = matchDistrict(historyString);
                if (!dString.equals("")) {
                    district = dString;
                    break;
                }
            }

            if (district.equals("")) {
                System.out.println("Error: must need a district");
                return;
            }
        }
        if (schoolPhase.equals("")) {
            //search from history questions
            for (String historyString : historyQuestions) {
                String schoolPhaseString = matchSchoolePhase(historyString);
                if (!schoolPhaseString.equals("")) {
                    schoolPhase = schoolPhaseString;
                    break;
                }
            }
            if (schoolPhase.equals("")) {
                System.out.println("Error: must need a school phase");
                return; 
            }
        }
         //this is message to LLM
         List<ChatCompletionMessage> messages = new ArrayList<>();
         //message add system info
         messages.add(new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(), assistentContent));
        
         if (isFirst) {
            //get matched files for the questions
            List<String> fileIds = getAllFileContentID(city, district, schoolPhase);

            int sizeId = fileIds.size();
            for (int i = 0; i < sizeId; i++) {
                String idString = fileIds.get(i);
                
                String fileContent = MoonshotAiUtils.getFileContent(idString);
                //message add user info, it is file content
                messages.add(new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(), fileContent));
            }
            //isFirst = false;
         }

        //add history questions here
        for (String historyString : historyQuestions) {
            messages.add(new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(), historyString));
        }

        //message add file context
        messages.add(new ChatCompletionMessage(ChatMessageRole.SYSTEM.value(), forContext));

        //message add user question now
        messages.add(new ChatCompletionMessage(ChatMessageRole.USER.value(), currentQuestion));

        //syncExecute(messages, client);

        //begin to call the LLM
        try {
            //8，32，128
            client.ChatCompletionStream(new ChatCompletionRequest(
                    "moonshot-v1-128k",
                    messages,
                    4 * 1024,
                    0.05f,
                    1
            )).subscribe(
                    streamResponse -> {
                        if (streamResponse.getChoices().isEmpty()) {
                            return;
                        }
                        for (ChatCompletionStreamChoice choice : streamResponse.getChoices()) {
                            String finishReason = choice.getFinishReason();
                            if (finishReason != null) {
                                System.out.println("finish reason: " + finishReason);
                                continue;
                            }
                            System.out.print(choice.getDelta().getContent());
                        }
                    },
                    error -> {
                        error.printStackTrace();
                    },
                    () -> {
                        System.out.println("complete");
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
           
        }

        //add the question to history
        historyQuestions.add(currentQuestion);
    }
}

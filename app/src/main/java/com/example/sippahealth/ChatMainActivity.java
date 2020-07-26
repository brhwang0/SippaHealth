package com.example.sippahealth;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.example.sippahealth.chat_objects.Message;
import com.example.sippahealth.holders.IncomingButtonMessageViewHolder;
import com.example.sippahealth.holders.OutgoingButtonMessageViewHolder;
import com.example.sippahealth.utils.AppUtils;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;


//please see messagefixtures and demomessagesactivity for more details
public class ChatMainActivity extends DemoMessagesActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener,
        DialogInterface.OnClickListener ,
        MessageHolders.ContentChecker<Message>,
        MessageInput.TypingListener{

    public static void open(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private MessagesList messagesList;

    //this is for old layout not using this anymore
//    private int _yDelta;
//    private GestureDetector mDetector;
//    private  View view1;
//    private int positioner1=0;
//    private ImageView imgs;
//    private RelativeLayout imgl;

    //testing the voice message

    //used for new ui as a touch check
    private int check=0;

    //the button content type id set it randomly to 5 for now
    private static final byte CONTENT_TYPE_BUTTON = 5;

    //new ui swipe action


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialise message adapter and input
//        findViewById(R.id.fragment_container).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                findViewById(R.id.fragment_container).getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//                ChatMainActivity.this.messagesList = (MessagesList) homeFragment.getView().findViewById(R.id.messagesList);
//                initAdapter();
//
//                MessageInput input = (MessageInput) homeFragment.getView().findViewById(R.id.input);
//                input.setInputListener(ChatMainActivity.this);
//                input.setTypingListener(ChatMainActivity.this);
//                input.setAttachmentsListener(ChatMainActivity.this);
//            }});

        //initialise message adapter and input
        initAdapter();

    }

    public void inithome(MessageInput input,MessagesList messagesList){
        input.setInputListener(ChatMainActivity.this);
        input.setTypingListener(ChatMainActivity.this);
        input.setAttachmentsListener(ChatMainActivity.this);
        ChatMainActivity.this.messagesList = messagesList;
        ChatMainActivity.this.messagesList.setAdapter(ChatMainActivity.super.messagesAdapter);
    }
    @Override
    public boolean onSubmit(CharSequence input) {
        super.messagesAdapter.addToStart(
                MessagesFixtures.getTextMessage(input.toString()), true);
        return true;
    }

    @Override
    public void onAddAttachments() {
//        super.messagesAdapter.addToStart(MessagesFixtures.getImageMessage(),true);
//                messagesAdapter.addToStart(MessagesFixtures.getVoiceMessage(),true);

        new AlertDialog.Builder(this)
                .setItems(R.array.view_types_dialog, this)
                .show();

    }

    private void initAdapter() {

        //add button holder to message adapter
        MessageHolders holders = new MessageHolders()
                .registerContentType(
                        CONTENT_TYPE_BUTTON,
                        IncomingButtonMessageViewHolder.class,
                        R.layout.item_custom_incoming_button_message,
                        OutgoingButtonMessageViewHolder.class,
                        R.layout.item_custom_outgoing_button_message,
                        this);

        //initilaise message adapter
        super.messagesAdapter = new MessagesListAdapter<>(super.senderId, holders, super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.registerViewClickListener(R.id.messageUserAvatar,
                new MessagesListAdapter.OnMessageViewClickListener<Message>() {
                    @Override
                    public void onMessageViewClick(View view, Message message) {
                        AppUtils.showToast(getApplicationContext(),
                                message.getUser().getName() + " avatar click",
                                false);
                    }
                });
      //  this.messagesList.setAdapter(super.messagesAdapter);
       // ArrayList<Message> messages = messagesAdapter.getSelectedMessages();
    }
    @Override
    public void onStartTyping() {
        Log.v("Typing listener", getString(R.string.start_typing_status));
    }

    @Override
    public void onStopTyping() {
        Log.v("Typing listener", getString(R.string.stop_typing_status));
    }

    //setup dialog to add image or button
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case 0:
                messagesAdapter.addToStart(MessagesFixtures.getImageMessage(), true);
                break;
//            case 1:
//                messagesAdapter.addToStart(MessagesFixtures.getVoiceMessage(), true);
//                break;
            case 1:
                messagesAdapter.addToStart(MessagesFixtures.getButton(), true);
                break;
        }
    }
    //check if button message
    @Override
    public boolean hasContentFor(Message message, byte type) {
        switch (type) {
            case CONTENT_TYPE_BUTTON:
                return message.getButton1() != null
                        && message.getButton1().getUrl() != null
                        && !message.getButton1().getUrl().isEmpty();
        }
        return false;
    }

    //on button click method
    public void button_method(View v){

        messagesAdapter.addToStart(
                MessagesFixtures.getImageMessage2(),true);
    }


}


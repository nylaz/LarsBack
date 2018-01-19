/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import entity.ChatRoom;
import entity.ChatUser;
import entity.Message;
import entity.PersistenceManager;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LasseLasseViking
 */
@Path("resources")
@Stateless
public class ChatResource {
    
    @EJB
    PersistenceManager pm;
    
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response createUser(JsonObject payload) {
        ChatUser user = new ChatUser();
        user.setUserName(payload.getString("userName"));
        user.setFullName(payload.getString("fullName"));
        user.setInfo(payload.getString("info"));
        user.setImage(payload.getString("image"));
        pm.createChatUser(user);
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getUsers() {
        List<ChatUser> users = pm.getChatUsers();
        return Response.status(Response.Status.OK).entity(users).build();
    }
    
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    public Response updateUser(JsonObject payload) {
        ChatUser user = new ChatUser();
        user.setUserName(payload.getString("userName"));
        user.setFullName(payload.getString("fullName"));
        user.setInfo(payload.getString("info"));
        user.setImage(payload.getString("image"));
        pm.updateChatUser(user);
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
    @Path("users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getSpecificUser(@PathParam("id") String payload) {
        ChatUser user = pm.getSingleChatUser(payload);
        return Response.status(Response.Status.OK).entity(user).build();
    }
    
    @Path("chat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response createChat(JsonObject payload) {
        ChatRoom chatroom = new ChatRoom();
        chatroom.setName(payload.getString("name"));
        chatroom.setDescription(payload.getString("description"));
        pm.createChatRoom(chatroom);
        return Response.status(Response.Status.OK).entity(chatroom).build();
    }
    
    @Path("chat")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getChatInfo() {
        return Response.status(Response.Status.OK).entity(pm.getChatroomMetadata(0)).build();
    }
    
    @Path("chat/messages")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getPublicMessages() {
        return Response.status(Response.Status.OK).entity(pm.getAllMessages(0)).build();
    }
    
    @Path("chat/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response postPublicMessage(JsonObject payload) {
        Message message = new Message();
        LocalDateTime time = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(time);
        message.setFrom(payload.getString("from"));
        message.setTo(payload.getString("to"));
        message.setMessage(payload.getString("message"));
        message.setTimestamp(timestamp);
        pm.postMessage(message);
        return Response.status(Response.Status.OK).build();
    }
    
    @Path("chats/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getPrivateChatInfo(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(pm.getChatroomMetadata(id)).build();
    }
    
    @Path("chats/{id}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getPrivateChatMessages(@PathParam("id") int id) {
        return Response.status(Response.Status.OK).entity(pm.getAllMessages(id)).build();
    }
    
    @Path("allchats")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response test(){
        return Response.status(Response.Status.OK).entity(pm.getAllChats()).build();
    }
}

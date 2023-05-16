const mongoose = require('mongoose');

const Schema = mongoose.Schema;

const userSchema = new Schema({

    email : {
        type : String,
        required : true
    },

    nick : {
        type : String,
        required : true
    },

    password : {
        type : String,
        required : true
    },

    level : {
        type : Number,
        required : true 
    },

    nextLevel : {
        type : Number,
        required : true,
    }

});

module.exports = mongoose.model('User', userSchema, 'users');
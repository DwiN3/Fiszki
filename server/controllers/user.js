const User = require('../models/user');
const { validationResult } = require('express-validator');
const bcrypt = require('bcrypt');

exports.singUp = async(req, res, next) => {

    const errors = validationResult(req);
    const email = req.body.email;
    const nick = req.body.nick;
    const password = req.body.password;
    const repeatedPassword = req.body.repeatedPassword;

    if(!errors.isEmpty()){
        const error = new Error('Wrong data!');
        error.statusCode = 400;
        return next(error);
    }

    if(password !== repeatedPassword){
        const error = new Error('Repeated password is diffrent than password!');
        error.statusCode = 400;
        return next(error);
    }

    try{
        const isEmail = await User.findOne({email : email});
        if(isEmail){
            const error = new Error('This email already exists!');
            error.statusCode = 400;
            throw (error);
        } 
    } catch(error){
        return next(error)
    }

    try{
        const isNick = await User.findOne({nick : nick});
        if(isNick){
            const error = new Error('This nick already exists!');
            error.statusCode = 400;
            throw (error);
        } 
    } catch(error){
        return next(error)
    }

    try{
        const passwords = await User.find().select({'password' : 1, _id : 0});
        for(index in passwords){
            if(await bcrypt.compare(password, passwords[index].password)){
                const error = new Error('This password already exists!');
                error.statusCode = 400;
                throw (error);
            } 
        }
    } catch(error){
        return next(error)
    }

    try{
        const hashedPassword = await bcrypt
            .hash(password, 12);
        
            const newUser = new User({
            email : email,
            nick : nick,
            password : hashedPassword
        });

        await newUser.save();

    } catch(error){
        if(!error.statusCode) error.statusCode = 500;
        return next(error);
    }
    return res.status(200).json('New user created succesful!');
}


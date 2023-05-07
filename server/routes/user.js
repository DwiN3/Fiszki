const express = require('express');
const userController = require('../controllers/user');
const { body } = require('express-validator');
const User = require('../models/user');
const bcrypt = require('bcrypt');

const router = express.Router();

router.post('/sing-up', 
    [
        body('email')
        .isEmail()
        .isLength({min : 10}),
        body('password')
            .isAlphanumeric()
            .isLength({ min : 8 }),
        body('repeatedPassword')
            .isAlphanumeric()
            .isLength({ min : 8 }),
        body('nick')
            .isAlphanumeric()
            .isLength({ min : 5 })
    ],
    userController.singUp);

router.post('/login', 
    [
        body('email')
            .isEmail()
            .isLength({ min : 10}),
        body('password')
            .isAlphanumeric()
            .isLength({ min : 8 })
    ],
    userController.login);

module.exports = router;


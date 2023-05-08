const Flashcardcollection = require('../models/flashcardsCollection');
const jwt = require('jsonwebtoken');

exports.getCollections = async(req, res, next) => {

    const token = req.get('Authorization').split(' ')[1];
    let flashcards;

    try{
        decodedToken = jwt.verify(token, 'flashcardsproject');
        flashcards = await Flashcardcollection.find({ author : decodedToken.nick})
    } catch(error){
        if(!error.statusCode) error.statusCode === 500;
        return next(error);
    }
    res.status(200).json(flashcards);

}
const Flashcardcollection = require('../models/flashcardsCollection');

exports.getCollections = async(req, res, next) => {

    let flashcards;

    try{
        flashcards = await Flashcardcollection.find({ author : req.user})
    } catch(error){
        if(!error.statusCode) error.statusCode === 500;
        return next(error);
    }
    res.status(200).json(flashcards);

}

exports.getCollection = async(req, res, next) => {

    const collectionName = req.params.collectionName;

    try{
        collection = await Flashcardcollection.findOne({ author : req.user, collectionName : collectionName}).populate('flashcards')
        if(!collection){
            const error = new Error("Collection doesn't exist");
            error.statusCode = 400;
            throw(error);
        }
    } catch(error){
        if(!error.statusCode) error.statusCode === 500;
        return next(error);
    }
    res.status(200).json(collection);

}
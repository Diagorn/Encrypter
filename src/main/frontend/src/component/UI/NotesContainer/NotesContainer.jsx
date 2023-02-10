import React from "react";
import { Image } from "react-bootstrap";
import NoteLetter from "../NoteLetter/NoteLetter";
import classes from './NotesContainer.module.css'

export default function(props) {

    return (
        <div className="notes">
            <div className="row">
                <div className="col-md-4 offset-md-4 text-center">
                    <Image 
                        src="https://media.istockphoto.com/id/1145887531/ru/%D0%B2%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D0%BD%D0%B0%D1%8F/%D1%82%D1%80%D0%B5%D0%B1%D0%BB-%D0%BA%D0%BB%D1%8E%D1%87-%D0%BD%D0%B0-%D0%B1%D0%B5%D0%BB%D0%BE%D0%BC-%D1%84%D0%BE%D0%BD%D0%B5.jpg?s=612x612&w=0&k=20&c=bjLmK0UQAdl3fxLGiubdjZHW3JrxyAWcX3drCuQxN70="
                        className={classes.KeyImage + " img-responsive"}
                    />
                </div>
            </div>
            <div className="row">
                {props.text.split(',').map(notesBlock => {
                    return (
                        <div className="col-md-3">
                            <NoteLetter
                                encryptedText={notesBlock}
                            />
                        </div>
                    )
                })}
                
            </div>
        </div>
    )
}
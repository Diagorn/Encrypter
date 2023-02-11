import React from "react";
import { useState, useEffect } from "react"
import classes from './NoteLetter.module.css'

export default function NoteLetter(props) {
    
    const [dots, setDots] = useState([])

    useEffect(() => {
        let dotsArr = splitText(props.encryptedText)
        setDots(dotsArr)
    }, [])

    function splitText(text) {
        let result = []

        //checking each symbol
        for (let i = 0; i < text.length; ++i) {
            let stringToAdd = '';
            let symbol = text[i]
            if (symbol === '(') {
                //extracting notes group in braces ()
                let currentPosition = i
                for (let j = currentPosition + 1; j < text.length; j++) {
                    //if we reached the end of notes group, skipping this part
                    if (text[j] === ')') {
                        i = j
                        break;
                    }

                    //else - adding another symbol of the group to the string
                    stringToAdd += text[j]
                }
            } else {
                //just adding the symbol as a single note
                stringToAdd = symbol
            }
            //getting rid of spaces if whitespace is passed
            if (stringToAdd.trim().length === 0) {
                continue
            }
            result.push({
                text: stringToAdd,
                id: i
            })
        }
        return result
    }

    function getDotClass(letter) {
        switch (letter) {
            case 'F':
                return classes.DotFirst
            case 'D':
                return classes.DotSecond
            case 'B':
                return classes.DotThird
            case 'G':
                return classes.DotFourth
            case 'E':
                return classes.DotFifth
            default:
                return ''
        }
    }

    function getBarsMarkup() {
        return (
            <div className={classes.BarsContainer}>
                <hr className={classes.Bar}/>
                <hr className={classes.Bar + ' ' + classes.BarSecond}/>
                <hr className={classes.Bar + ' ' + classes.BarThird}/>
                <hr className={classes.Bar + ' ' + classes.BarFourth}/>
                <hr className={classes.Bar + ' ' + classes.BarFifth}/>
            </div>
        )
    }

    function getDotsMarkup() {
        return (
            <div className={classes.DotsContainer}>
                {splitText(props.encryptedText).map(dot => {
                    if (dot.text.length > 1) {
                        return (
                            <div key={dot.id} className={classes.DotsColumn}>
                                {
                                    dot.text.split('').map(dotLetter => {
                                        return <div key={dotLetter} className={classes.Dot + ' ' + getDotClass(dotLetter)}/>
                                    })
                                }
                            </div>
                        )
                    } else {
                        return (
                            <div key={dot.id} className={classes.DotsColumn}>
                                <div key={dot.id} className={classes.Dot + ' ' + getDotClass(dot.text)}></div>
                            </div>
                        )
                    }
                })}
            </div>
        )
    }

    return (
        <div className={classes.NoteLetter}>
            {getBarsMarkup()}
            {getDotsMarkup()}
        </div>
    )
}
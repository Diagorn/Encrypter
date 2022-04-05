import React from 'react'
import classes from './TextArea.module.css'

export const TextArea = (props) => {
  return (
    <textarea className={"form-control " + classes.TextArea} {...props}>
      {props.text}
    </textarea>
  )
}

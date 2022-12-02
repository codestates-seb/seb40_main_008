"use client"
import React from 'react'
import { delMyClass, editMyClass } from '../../utils/api/classEditDel';
import styles from './DelEditbtn.module.css';

const DelEditbtn = (props: { id: number }) => {
    return (
        <>
            <button className={styles.btn} onClick={() => editMyClass(props.id)}>수정</button>
            <button className={styles.btn} onClick={() => delMyClass(props.id)}>삭제</button>
        </>
    )
}

export default DelEditbtn

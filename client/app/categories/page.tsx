import React from 'react'
import BaseNavbar from '../../components/BaseNavBar/BaseNavbar'
import styles from './categories.module.css'

const Categories = () => {
    return (
        <div className='main'>
            <BaseNavbar />
            <div className={styles.categorytop}>
                <span className={styles.categoryleft}>
                    <span className={styles.link}>디지털 드로잉</span>
                    <span className={styles.link}>드로잉</span>
                    <span className={styles.link}>공예</span>
                    <span className={styles.link}>요리/음료</span>
                    <span className={styles.link}>베이킹/디저트</span>
                </span>
                <span className={styles.categoryright}>
                    <span className={styles.link}>음악</span>
                    <span className={styles.link}>운동</span>
                    <span className={styles.link}>라이프스타일</span>
                    <span className={styles.link}>사진/영상</span>
                </span>
            </div>

            <div className={styles.line}></div>

            <div className={styles.category}>
                <span className={styles.categoryleft}>
                    <span className={styles.link}>금융/재테크</span>
                    <span className={styles.link}>창업/부업</span>
                </span>
                <span className={styles.categoryright}>
                    <span className={styles.link}>성공마인드</span>
                </span>
            </div>

            <div className={styles.line}></div>

            <div className={styles.category}>
                <span className={styles.categoryleft}>
                    <span className={styles.link}>프로그래밍</span>
                    <span className={styles.link}>데이터사이언스</span>
                    <span className={styles.link}>제품 기획</span>
                    <span className={styles.link}>비즈니스</span>
                </span>
                <span className={styles.categoryright}>
                    <span className={styles.link}>생산성</span>
                    <span className={styles.link}>마케팅</span>
                    <span className={styles.link}>디자인</span>
                    <span className={styles.link}>영상/3D</span>
                </span>
            </div>

            <div className={styles.line}></div>

            <div className={styles.category}>
                <span className={styles.categoryleft}>
                    <span className={styles.link}>영어</span>
                    <span className={styles.link}>제2 외국어</span>
                </span>
                <span className={styles.categoryright}>
                    <span className={styles.link}>외국어 시험</span>
                </span>
            </div>

            <div className={styles.line}></div>

            <div className={styles.category}>
                <span className={styles.categoryleft}>
                    <span className={styles.link}>아이 교육</span>
                </span>
                <span className={styles.categoryright}>
                    <span className={styles.link}>부모 교육</span>
                </span>
            </div>

        </div>
    )
}

export default Categories

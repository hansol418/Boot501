//axios 통신 테스트용 함수
async function getTest(bno) {
    const result = await axios.get(`/replies/list/${bno}`)
    // console.log("result 확인 : " + result)
    // 데이터 확인용
    // return result.data
    return result
}
//댓글 목록 출력하는 함수.
// bno: 현재 게시글 번호
// page: 페이지번호
// size: 페이지당 출력 갯수
// goLast: 마지막에 최신 댓글이 있다면, 마지막 댓글로 가는 여부.
async function getList({bno,page,size,goLast}){
    const result = await axios.get(`/replies/list/${bno}`,{params : {page,size}})
    return result.data
}





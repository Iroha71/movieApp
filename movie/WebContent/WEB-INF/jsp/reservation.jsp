<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>予約画面</title>
<link rel="stylesheet" href="lib/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/reservation.css">
<script src="lib/vue.min.js"></script>
</head>
<body>
<div class="container-fluid" id="app">
<%
	String[] sheets=new String[100];
	sheets = (String[])request.getAttribute("sheets");
	int sheetCount=0;
%>
	<form action = "MovieReservationController" method = "get" id="reserveForm" class="hidden">
		<input type="hidden" name="member"value=1>
		<input type="hidden" name="term" value = 1>
		<input type="hidden" name="theater" value=1>
		<input type="hidden" name ="screen" value=1>
		<input type="hidden" name = "fee" value=1 v-model="reserveSheetType" id="inputFee">
		<input type="text" name = "sheet" v-model="reserveSheetNum" id="inputSheet">
	</form>
	<article class="row navbar">
		<div class="col-sm-4 select-sheet">
			座席選択
		</div>
		<div class="col-sm-4 select-ticket">
			チケット選択
		</div>
		<div class="col-sm-4 confirm-buy">
			購入情報のご確認
		</div>
	</article>
	<article class="row content" v-if="status=='selectSheet'">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<table>
			<%for(int i=0;i<10;i++){ %>
				<tr>
				<%for(int j=(i*10)+1;j<=(i+1)*10;j++){ %>
					<%if(Integer.parseInt(sheets[sheetCount])==j-1){ %>
					<td>
						<button class="sheet-div active-sheet" @click="selectSheet(<%=j %>)"><%=j %></button>
						<select class="input-ticket" v-if="selectedSheet===<%=j %>" @change="reserve(<%=j%>)" v-model="ticketType">
							<option value="券種を選択" disabled>券種を選択</option>
							<option value="大人">大人</option>
							<option value="取り消し">取り消し</option>
						</select>
					</td>
					<%sheetCount++; %>
					<%}else{ %>
					<td>
						<button class="sheet-div disable-sheet" disabled></button>
					</td>
					<%} %>
				<%} %>
				</tr>
			<%} %>
			</table>
			<button type="button" @click="showModal(true)" class="btn btn-primary">次へ</button>
		</div>
		<section class="modal-area col-sm-12 center" v-if="isModal">
			<div class="check-modal center">
				こちらで予約しますか？
				<table>
					<tr>
						<th>タイトル</th>
						<th>チケット料金</th>
						<th>座席番号</th>
						<th>上映時間</th>
					</tr>

				</table>
				<button type="button" @click="submitReserve" class="btn btn-primary">取り消し</button>
				<button type="button" @click="showModal(false)" class="btn btn-worning">キャンセル</button>
			</div>
		</section>
	</article>
</div>
</body>
<script>
var app=new Vue({
	el: '#app',
	data:{
		status: 'selectSheet',
		selectedSheet: -1,
		reserveSheet:[],
		sheetNumber: 2,
		ticketType: '券種を選択',
		isClicked: false,
		sheets: [],
		isModal: false,
		reserveSheetNum: [],
		reserveSheetType: [],
	},
	methods:{
		showModal:function(isShow){
			this.isModal=isShow
		},
		selectSheet:function(index){
			this.selectedSheet=index;
			this.ticketType="券種を選択"
		},
		reserve:function(index){
			/* 予約席を1席以上選択しているか? */
			if(this.reserveSheet.length>0){
				//予約席番号のみ抽出
				let tempReserveNum=[]
				for(let i=0;i<this.reserveSheet.length;i++){
					tempReserveNum.push(this.reserveSheet[i]['num'])
				}
				const reserveIndex=tempReserveNum.indexOf(index);
				if(reserveIndex>=0){
					/* チケット種類変更か予約取り消しか? */
					if(this.ticketType==="取り消し"){
						console.log("取り消し処理")
						this.reserveSheet.splice(reserveIndex,1);
						this.reserveSheetNum.splice(reserveIndex,1);
						this.reserveSheetType.splice(reserveIndex,1);
						console.log("削除");
					}else{
						console.log("予約処理")
						this.reserveSheet[reserveIndex]['type']=this.ticketType;
						this.reserveSheetType[reserveIndex]=this.ticketType;
					}
				}else{
					//予約したことがない席を選択した -> 予約配列に追加
					console.log(index)
					const sheet={num: index,type: this.ticketType};
					this.reserveSheet.push(sheet);
					this.reserveSheetNum.push(index)
					this.reserveSheetType.push(this.ticketType)
				}
			}else{
				const sheet={num: index,type: this.ticketType}
				this.reserveSheet.push(sheet)
				this.reserveSheetNum.push(index);
				this.reserveSheetType.push(this.ticketType)
			}
			this.selectedSheet=-1;
			console.log(this.reserveSheetType)
			console.log(this.reserveSheetNum)
		},
		submitReserve:function(){
			const inputSheet=document.getElementById('inputSheet')
			inputSheet.value=this.reserveSheetNum
			const inputFee=document.getElementById('inputFee')
			inputFee.value=this.reservationSheetType
			console.log(inputSheet.value)
			const reserveForm=document.getElementById('reserveForm')
			reserveForm.submit()
		}
	}
})
</script>
</html>
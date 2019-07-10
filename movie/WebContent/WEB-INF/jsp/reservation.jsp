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
	<article class="row progress-status hidden">
		<form action = "MovieReservationController" method = "get">
		<input type="hidden"  name="member"value=1>
		<input type="hidden" name="term" value = 1>
		<input type="hidden" name="theater" value=1>
		<input type="hidden" name ="screen" value=1>
		<input type="hidden" name = "sheet" value=1>
		<input type="hidden" name = "sheet" value=2>
		<input type = "text" name ="sheet" value = 3><%=sheets[3] %>
		<input type="hidden" name = "fee" value=1>
		<input type="hidden" name = "fee" value=1>
		<input type="hidden" name = "fee" value=1>
		<input type ="submit" name="button" value="予約する">
		</form>
	</article>
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
			<button type="button" @click="changeStatus('selectTicket')" class="btn btn-primary">次へ</button>
		</div>
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
		sheets: []
	},
	methods:{
		changeStatus:function(status){
			this.status=status;
		},
		selectSheet:function(index){
			this.selectedSheet=index;
			this.ticketType="券種を選択"
		},
		reserve:function(index){
			/* 予約席を1席以上選択しているか? */
			if(this.reserveSheet.length>0){
				let reserveSheetNum=[]
				//予約席番号のみ抽出
				for(let i=0;i<this.reserveSheet.length;i++){
					reserveSheetNum.push(this.reserveSheet[i]['num'])
				}
				const reserveIndex=reserveSheetNum.indexOf(index);
				if(reserveIndex>=0){
					/* チケット種類変更か予約取り消しか? */
					if(this.ticketType==="取り消し"){
						console.log("取り消し処理")
						this.reserveSheet.splice(reserveIndex,1);
						console.log("削除");
					}else{
						console.log("予約処理")
						this.reserveSheet[resetveIndex]['type']=this.ticketType;
					}
				}else{
					//予約したことがない席を選択した -> 予約配列に追加
					const sheet={num: index,type: this.ticketType};
					this.reserveSheet.push(sheet);
				}
			}else{
				const sheet={num: index,type: this.ticketType}
				this.reserveSheet.push(sheet)
			}
			console.log(this.reserveSheet)
			this.selectedSheet=-1;
		}
	}
})
</script>
</html>
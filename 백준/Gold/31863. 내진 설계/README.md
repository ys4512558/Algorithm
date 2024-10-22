# [Gold V] 내진 설계 - 31863 

[문제 링크](https://www.acmicpc.net/problem/31863) 

### 성능 요약

메모리: 47520 KB, 시간: 424 ms

### 분류

그래프 이론, 그래프 탐색, 구현, 시뮬레이션

### 제출 일자

2024년 10월 8일 22:47:25

### 문제 설명

<p>오늘 새벽, 갑자기 규모 5.0 지진이 발생했다. 지진이 발생한 진원지는 $N \times M$ 격자 모양의 지역 중 한 곳이다. 진원지에서 발생한 지진을 본진, 건물이 무너졌을 때 발생하는 약한 지진을 여진이라고 하자. 본진은 진원지를 기준으로 상하좌우 각 방향으로 $2$칸까지 뻗어나가며, 여진은 상하좌우로 $1$칸까지 뻗어나간다. 본진과 여진은 건물에 영향을 준다. 내진 설계가 되어 있지 않은 건물은 지진이 도달한 즉시 무너지지만, 내진 설계가 되어 있는 건물은 지진이 $2$번 도달하면 무너진다. 본진과 여진이 뻗어나가는 도중 지진 방파제를 만나거나 격자 모양의 지역 밖으로 나가면 더 이상 뻗어나가지 않는다. 예제1에 대한 지진의 이동은 아래와 같다.</p>

<table class="table table-bordered td-center">
	<tbody>
		<tr>
			<td><img alt="" src="" style="height: 280px; width: 350px;"></td>
			<td><img alt="" src="" style="height: 280px; width: 350px;"></td>
		</tr>
		<tr>
			<td>(1)</td>
			<td>(2)</td>
		</tr>
		<tr>
			<td><img alt="" src="" style="height: 280px; width: 350px;"></td>
			<td><img alt="" src="" style="height: 280px; width: 350px;"></td>
		</tr>
		<tr>
			<td>(3)</td>
			<td>(4)</td>
		</tr>
	</tbody>
</table>

<p>빠른 재해 복구를 위해 지진의 피해를 확인하고자 한다. 지진으로 인해 무너진 건물의 개수와 무너지지 않은 건물의 개수를 구해보자.</p>

### 입력 

 <p>첫째 줄에 정수 $N(2 \leq N \leq 1 \, 000)$과 $M(2 \leq M \leq 1 \, 000)$이 공백으로 구분되어 주어진다.</p>

<p>둘째 줄부터 $N$개의 줄에 걸쳐 길이 $M$의 문자열이 주어진다. 문자열을 이루는 문자는 아래 5종류이며, 진원지는 1개만 주어진다.</p>

<ul>
	<li><span style="color:#e74c3c;"><code>@</code></span>: 진원지</li>
	<li><span style="color:#e74c3c;"><code>.</code></span>: 일반 도로</li>
	<li><span style="color:#e74c3c;"><code>*</code></span>: 내진 설계가 되어있지 않은 건물</li>
	<li><span style="color:#e74c3c;"><code>#</code></span>: 내진 설계가 되어있는 건물</li>
	<li><span style="color:#e74c3c;"><code>|</code></span>: 방파제</li>
</ul>

### 출력 

 <p>무너진 건물의 개수와 무너지지 않은 건물의 개수를 공백으로 구분하여 한 줄에 출력한다.</p>


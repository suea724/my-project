<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<html>
<head>
  <title>Devcom</title>
  <style>
    .tagify__input {
      min-height: 30px;
    }

    .tags-look .tagify__dropdown__item{
      display: inline-block;
      border-radius: 3px;
      padding: .3em .5em;
      border: 1px solid #CCC;
      background: #F3F3F3;
      margin: .2em;
      font-size: .85em;
      color: black;
      transition: 0s;
    }

    .tags-look .tagify__dropdown__item--active{
      color: black;
    }

    .tags-look .tagify__dropdown__item:hover{
      background: lightyellow;
      border-color: gold;
    }

    input[name=input-custom-dropdown] {
      padding: 100px;
    }
  </style>
</head>
<body>
<div class="container">
  <main>
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <section>
      <form method="post" action="/devcom/study/add.do">
      <table class="table table-borderless" id="study-add">
        <tr>
          <td>
            <h6>모집 유형</h6>
            <select class="form-control" name="category">
              <option value="" disabled selected>선택</option>
              <option value="프로젝트">프로젝트</option>
              <option value="스터디">스터디</option>
            </select>
          </td>
          <td>
            <h6>진행 방식</h6>
            <select class="form-control" name="progressway">
              <option value="" disabled selected>선택</option>
              <option value="온라인">온라인</option>
              <option value="오프라인">오프라인</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            <h6>모집 인원</h6>
            <input type="number" min="1" max="100" class="form-control" placeholder="입력" name="recruitment" />
          </td>
          <td>
            <h6>시작 날짜</h6>
            <input type="date" class="form-control" name="startdate"/>
          </td>
        </tr>
        <tr>
          <td>
          <h6>진행 기간</h6>
          <select class="form-control" name="duration">
            <option value="미정">미정</option>
            <c:forEach begin="1" end="24" var="month" step="1">
              <option value="${month}">${month}개월</option>
            </c:forEach>
          </select>
          </td>
          <td>
            <h6>연락 방법</h6>
            <input type="text" class="form-control" placeholder="입력" name="contact" />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>기술 스택</h6>
            <input name='input-custom-dropdown' class='some_class_name' placeholder='기술 스택을 입력해주세요.'>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>제목</h6>
            <input type="text" name="title" id="title" class="form-control" placeholder="제목을 입력해주세요.">
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <h6>내용</h6>
            <textarea class="form-control" name="content" placeholder="내용을 입력해주세요." style="height: 300px"></textarea>
          </td>
        </tr>
      </table>
        <input type="submit" value="등록하기" class="btn btn-primary right">
      </form>

    </section>
  </main>
</div>
</body>

<script>
  var input = document.querySelector('input[name="input-custom-dropdown"]'),
          // init Tagify script on the above inputs
          tagify = new Tagify(input, {
            whitelist: ["A# .NET", "A# (Axiom)", "A-0 System", "A+", "A++", "ABAP", "ABC", "ABC ALGOL", "ABSET", "ABSYS", "ACC", "Accent", "Ace DASL", "ACL2", "Avicsoft", "ACT-III", "Action!", "ActionScript", "Ada", "Adenine", "Agda", "Agilent VEE", "Agora", "AIMMS", "Alef", "ALF", "ALGOL 58", "ALGOL 60", "ALGOL 68", "ALGOL W", "Alice", "Alma-0", "AmbientTalk", "Amiga E", "AMOS", "AMPL", "Apex (Salesforce.com)", "APL", "AppleScript", "Arc", "ARexx", "Argus", "AspectJ", "Assembly language", "ATS", "Ateji PX", "AutoHotkey", "Autocoder", "AutoIt", "AutoLISP / Visual LISP", "Averest", "AWK", "Axum", "Active Server Pages", "ASP.NET", "B", "Babbage", "Bash", "BASIC", "bc", "BCPL", "BeanShell", "Batch (Windows/Dos)", "Bertrand", "BETA", "Bigwig", "Bistro", "BitC", "BLISS", "Blockly", "BlooP", "Blue", "Boo", "Boomerang", "Bourne shell (including bash and ksh)", "BREW", "BPEL", "B", "C--", "C++ – ISO/IEC 14882", "C# – ISO/IEC 23270", "C/AL", "Caché ObjectScript", "C Shell", "Caml", "Cayenne", "CDuce", "Cecil", "Cesil", "Céu", "Ceylon", "CFEngine", "CFML", "Cg", "Ch", "Chapel", "Charity", "Charm", "Chef", "CHILL", "CHIP-8", "chomski", "ChucK", "CICS", "Cilk", "Citrine (programming language)", "CL (IBM)", "Claire", "Clarion", "Clean", "Clipper", "CLIPS", "CLIST", "Clojure", "CLU", "CMS-2", "COBOL – ISO/IEC 1989", "CobolScript – COBOL Scripting language", "Cobra", "CODE", "CoffeeScript", "ColdFusion", "COMAL", "Combined Programming Language (CPL)", "COMIT", "Common Intermediate Language (CIL)", "Common Lisp (also known as CL)", "COMPASS", "Component Pascal", "Constraint Handling Rules (CHR)", "COMTRAN", "Converge", "Cool", "Coq", "Coral 66", "Corn", "CorVision", "COWSEL", "CPL", "CPL", "Cryptol", "csh", "Csound", "CSP", "CUDA", "Curl", "Curry", "Cybil", "Cyclone", "Cython", "Java", "Javascript", "M2001", "M4", "M#", "Machine code", "MAD (Michigan Algorithm Decoder)", "MAD/I", "Magik", "Magma", "make", "Maple", "MAPPER now part of BIS", "MARK-IV now VISION:BUILDER", "Mary", "MASM Microsoft Assembly x86", "MATH-MATIC", "Mathematica", "MATLAB", "Maxima (see also Macsyma)", "Max (Max Msp – Graphical Programming Environment)", "Maya (MEL)", "MDL", "Mercury", "Mesa", "Metafont", "Microcode", "MicroScript", "MIIS", "Milk (programming language)", "MIMIC", "Mirah", "Miranda", "MIVA Script", "ML", "Model 204", "Modelica", "Modula", "Modula-2", "Modula-3", "Mohol", "MOO", "Mortran", "Mouse", "MPD", "Mathcad", "MSIL – deprecated name for CIL", "MSL", "MUMPS", "Mystic Programming L"],
            maxTags: 10,
            dropdown: {
              maxItems: 20,           // <- mixumum allowed rendered suggestions
              classname: "tags-look", // <- custom classname for this dropdown, so it could be targeted
              enabled: 0,             // <- show suggestions on focus
              closeOnSelect: false    // <- do not hide the suggestions dropdown once an item has been selected
            }
          })
</script>
</html>
